import { Component } from '@angular/core';
import { NavigationService } from './common-services';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'pizzeria';

  constructor(private navigation: NavigationService){
  }
  ngOnInit() {

  }
}
