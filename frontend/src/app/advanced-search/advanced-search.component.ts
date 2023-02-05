import { Component, OnInit,Output,EventEmitter } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Book } from 'src/app/models/Book';
import { BookService } from 'src/app/services/book.service';
@Component({
  selector: 'app-advanced-search',
  templateUrl: './advanced-search.component.html',
  styleUrls: ['./advanced-search.component.scss']
})
export class AdvancedSearchComponent implements OnInit {

  searchText = "";
  constructor(public bookService: BookService) {
  }

  @Output() searchcriteria = new EventEmitter<String>();
  searchThis() {
    //this.searchcriteria.emit(this.searchword)
    console.log(this.searchText)
    this.bookService.getAdvancedSearchResult("Jefferson",1)
      .subscribe((book: Book[]) => {
             console.log("Book ");
           },
           (err) => {
             alert("failed loading json data");
           }
      );
  }

  ngOnInit(): void {
  }



}
