import { Component, OnInit,Output,EventEmitter } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Book } from 'src/app/models/Book';
import { BookService } from 'src/app/services/book.service';
@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {

  searchText = "";
  pageNumber = 0;
  books!: Book[];
  book: any = [];

  radioValue: string = "basic";

  constructor(public bookService: BookService) {
  }
  searchThis(searchText: string,pageNumber: number) {
    console.log("radio val: ", this.radioValue);
    if(this.radioValue == "statistic")
    {
      this.bookService.getStatisticSearchResult(searchText,pageNumber)
        .subscribe((book: Book[]) => {
            this.books = book;
            console.log(book);
          },
          (err) => {
            alert("failed loading json data");
          }
        );
    }
    else if(this.radioValue == "advanced")
    {
      this.bookService.getAdvancedSearchResult(searchText,pageNumber)
        .subscribe((book: Book[]) => {
            this.books = book;
            console.log(book);
          },
          (err) => {
            alert("failed loading json data");
          }
        );
    }
    else
    {
      this.bookService.getBasicSearchResult(searchText,pageNumber)
        .subscribe((book: Book[]) => {
            this.books = book;
          },
          (err) => {
            alert("failed loading json data");
          }
        );
    }
  }

  onItemChange(value: string) {
    this.radioValue = value;
  }

  ngOnInit(): void {
  }



}
