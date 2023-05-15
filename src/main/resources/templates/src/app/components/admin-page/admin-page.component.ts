import {Component} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {MatSnackBar} from "@angular/material/snack-bar";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
    selector: 'app-admin-page',
    templateUrl: './admin-page.component.html',
    styleUrls: ['./admin-page.component.css']
})


export class AdminPageComponent {

    strings: string[] = [];
    form: FormGroup;

    constructor(public fb: FormBuilder, private http: HttpClient, private snackBar: MatSnackBar) {
        this.form = this.fb.group({
            username: [''],
            role:[''],
            subject: [''],
        });
    }

    showSnackbar(content: string, action: string, duration: number) {
        this.snackBar.open(content, action, {
            duration: duration,
            verticalPosition: 'bottom',
            horizontalPosition: 'left',
            panelClass: ['snackbar']
        });
    }

    ngOnInit() {
        this.http.get<string[]>('http://localhost:8082/class/getMaterie').subscribe(data => {
            this.strings = data;
            console.log(this.strings);
        });
    }

    uploadCustomersData(fileInput: any) {
        const file = fileInput.files[0];
        const formData = new FormData();
        formData.append('file', file, file.name);

        this.http.post('http://localhost:8082/user/upload-customers-data', formData).subscribe({
                next: (response) => {
                    console.log(response);
                    this.showSnackbar('Incarcare fisier .xlsx realizata cu succes!', 'Ok', 5000);
                },
                error: (error) => {
                    console.log(error);
                    this.showSnackbar('EROARE: fisierul .xlsx contine utilizatori deja inregistrati SAU este gol.', 'Ok', 5000);
                },
            }
        );
    }

    enrollSubject() {
        const formData: any = new FormData();

        const username = this.form.get('username')!.value;
        const role = this.form.get('role')!.value;
        const subject = this.form.get('subject')!.value;

        console.log(username + " " + role + " " + subject);

        if (username != "" && subject != "") {
            formData.append('username', username);
            formData.append('subject', subject)

            let url = 'http://localhost:8082/class/';
            if (role == 'student') {
                url = url.concat('addStudent');
            } else {
                url = url.concat('addProfessorToClass');
            }

            console.log(url);

            this.http.post(url, formData)
                .subscribe({
                    next: (response) => {
                        console.log(response);
                        this.showSnackbar('Utilizatorul ' + username + ' a fost inrolat la materia ' + subject, 'Ok', 5000);
                    },
                    error: (error) => {
                        console.log(error);
                        this.showSnackbar("EROARE: Utilizatorul " + username + " este deja inrolat la materia " + subject, 'Ok', 5000);
                    },
                });
        }
    }
}
