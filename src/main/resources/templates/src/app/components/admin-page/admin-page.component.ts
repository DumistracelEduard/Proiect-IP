import {Component, NgModule, Renderer2, ViewChild} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FormBuilder, FormGroup, FormsModule} from '@angular/forms';
import {BrowserModule} from "@angular/platform-browser";

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})



export class AdminPageComponent {

  // constructor(private http: HttpClient) {}

  form: FormGroup;
  constructor(public fb: FormBuilder, private http: HttpClient) {
    this.form = this.fb.group({
      name: [''],
      avatar: [null],
    });
  }

  ngOnInit() {}

  uploadCustomersData(fileInput: any) {
    const file  = fileInput.files[0];
    const formData = new FormData();
    formData.append('file', file, file.name);

    this.http.post('http://localhost:8082/user/upload-customers-data', formData).subscribe(response => {
      console.log(response);
    });
  }

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
