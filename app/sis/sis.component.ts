import { Component, OnInit } from '@angular/core';
import { Global } from '../global';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-sis',
  templateUrl: './sis.component.html',
  styleUrls: ['./sis.component.css']
})

export class SisComponent implements OnInit
{
  title = "SIS Report Generator";
  prefix = "";
  minGpa = "";
  sortBy = "NONE";
  status;
  result;
  error;
  showStatus: boolean;

  constructor(private http: HttpClient)
  {
    this.showStatus = false;
  }

  ngOnInit() { }

  submitted = false;
  onSubmit()
  {
    this.submitted = true;

    this.http.get(Global.SIS_URL + "?prefix="+this.prefix+"&minGpa="+this.minGp$
            .subscribe(data =>
            {
               let resp = JSON.parse(JSON.stringify(data));
               this.status = resp.status;
               if (this.status == true)
               {
                  this.result = resp.data;
               }
               else
               {
                 this.showStatus = true;
                 this.error = resp.data;
               }
            });
  }
}


