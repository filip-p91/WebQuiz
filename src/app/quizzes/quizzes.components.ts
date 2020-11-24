import { Component, OnInit, Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'quizzes',
  templateUrl: 'quizzes.component.html',
  styleUrls: ['./quizzes.component.css']
})

@Injectable()
export class Quizzes implements OnInit {
  quizzes: any;

  constructor(private httpClient: HttpClient) { }

  ngOnInit() {
    this.httpClient.get('http://localhost:8080/quizzes') // <1>
        .subscribe(data => this.quizzes = data);
  }
}