import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Global } from '../global';

@Component({
  selector: 'app-prime',
  templateUrl: './prime.component.html',
  styleUrls: ['./prime.component.css']
})

export class PrimeComponent implements OnInit
{
  title = "Prime Number Finder";
  min;
  max;
  status;
  result;
  showStatus: boolean;

  constructor(private http: HttpClient)
  {
    this.showStatus = false;
  }

  ngOnInit() { }

  submitted = false;
  onSubmit()
  {
    this.submitted = true; // button clicked

    // attributes bound with [(ngModel)]

    // fetch answer
    this.http.get(Global.PRIME_URL + "?min=" + this.min + "&max=" + this.max)
            .subscribe(data =>
            {
                //alert(JSON.stringify(data));
               let resp = JSON.parse(JSON.stringify(data));
               this.status = resp.status;
               this.result = resp.data;
               // show status on error
               if (this.status == false)
               {
                  this.showStatus = true;
                  this.result = resp.data;
               }
            }
      );
  }

  onNextSubmit()
  {
    this.min = this.result; // set min to last
    this.onSubmit();
  }

}
