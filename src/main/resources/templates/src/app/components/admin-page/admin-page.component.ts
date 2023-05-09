import {Component} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FormGroup} from '@angular/forms';

@Component({
    selector: 'app-admin-page',
    templateUrl: './admin-page.component.html',
    styleUrls: ['./admin-page.component.css']
})


export class AdminPageComponent {

    strings: string[] = [];
    // form: FormGroup;

    constructor(private http: HttpClient) {
    }

    // var express = require('express')
    // var cors = require('cors')
    // var app = express()
    //
    // app.use(cors())

    ngOnInit() {
        this.http.get<string[]>('http://localhost:8082/class/getMaterie').subscribe(data => {
            this.strings = data;
            console.log(this.strings);
        });
    }

    // constructor(public fb: FormBuilder, private http: HttpClient) {
    //     this.form = this.fb.group({
    //         name: [''],
    //         avatar: [null],
    //     });
    // }


    // uploadCustomersData(fileInput: any) {
    //     const file = fileInput.files[0];
    //     const formData = new FormData();
    //     formData.append('file', file, file.name);
    //
    //     this.http.post('http://localhost:8082/user/upload-customers-data', formData).subscribe(response => {
    //         console.log(response);
    //     });
    // }


    // manualEnroll() {
    //   const formData: any = new FormData();
    //   formData.append('name', this.form.get('name').value);
    //   formData.append('avatar', this.form.get('avatar').value);
    //   this.http
    //     .post('http://localhost:8082/class/addStudent', formData)
    //     .subscribe({
    //       next: (response) => console.log(response),
    //       error: (error) => console.log(error),
    //     });
    // }

}
