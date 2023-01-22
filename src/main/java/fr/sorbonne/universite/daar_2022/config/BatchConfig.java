package fr.sorbonne.universite.daar_2022.config;

import fr.sorbonne.universite.daar_2022.model.Book;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public Job readCSVFileJob(JobRepository jobRepository, Step step) {
        return new JobBuilder("readCSVFileJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step", jobRepository)
                .<Book, Book>chunk(5, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Value("classpath:/static/gutenberg_data.csv")
    private Resource inputResource;

    @Bean
    public ItemProcessor<Book, Book> processor() {
        return new DBLogProcessor();
    }

    @Bean
    public FlatFileItemReader<Book> reader() {
        FlatFileItemReader<Book> itemReader = new FlatFileItemReader<Book>();
        itemReader.setLineMapper(lineMapper());
        itemReader.setLinesToSkip(1);
        itemReader.setResource(inputResource);
        return itemReader;
    }

    @Bean
    public DataSource dataSource() {

        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        return embeddedDatabaseBuilder.addScript("classpath:org/springframework/batch/core/schema-drop-h2.sql")
                .addScript("classpath:org/springframework/batch/core/schema-h2.sql")
                .addScript("classpath:book.sql")
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<Book> writer() {

        JdbcBatchItemWriter<Book> itemWriter = new JdbcBatchItemWriter<Book>();

        itemWriter.setDataSource(dataSource());
        itemWriter.setSql("INSERT INTO BOOK (TITLE, AUTHOR, LINK, ID, BOOKSHELF, TEXT) VALUES (:title, :author, :link, :id, :bookshelf, :text)");
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Book>());
        return itemWriter;
    }

    @Bean
    public LineMapper<Book> lineMapper() {
        DefaultLineMapper<Book> lineMapper = new DefaultLineMapper<Book>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        BeanWrapperFieldSetMapper<Book> fieldSetMapper = new BeanWrapperFieldSetMapper<Book>();

        lineTokenizer.setNames(new String[]{"title", "author", "link", "id", "bookshelf", "text"});
        lineTokenizer.setIncludedFields(new int[]{0, 1, 2, 3, 4, 5});
        fieldSetMapper.setTargetType(Book.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }
}
