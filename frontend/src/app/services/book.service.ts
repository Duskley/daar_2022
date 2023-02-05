import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Book } from 'src/app/models/Book';
@Injectable({
  providedIn: 'root'
})
export class BookService {
    baseUrl = 'http://localhost:8080/'

    getAdvancedSearchResult(text: string, pageNumber: any) {
    const params = {regex: text, page:pageNumber }
      return this.http.get<Book[]>(this.baseUrl+"advanced-search",{params: params} )//{headers: headers, params: params}
    }
    getBasicSearchResult(text: string, pageNumber: any) {
      const params = {query: text, page:pageNumber }
      return this.http.get<Book[]>(this.baseUrl+"basic-search",{params: params} )
    }
    getStatisticSearchResult(word: string, pageNumber: any) {
      const params = {word: word, page:pageNumber }
      return this.http.get<Book[]>(this.baseUrl+"statistic-search",{params: params} )//{headers: headers, params: params}
    }

    constructor(private http: HttpClient) { }

}
