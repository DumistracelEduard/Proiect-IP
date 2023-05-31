import {Component} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {MatSnackBar} from "@angular/material/snack-bar";
import {FormBuilder, FormGroup} from "@angular/forms";
import {StudentGradeObject} from "../StudentGradeObject";

@Component({
    selector: 'app-admin-page',
    templateUrl: './admin-page.component.html',
    styleUrls: ['./admin-page.component.css']
})


export class AdminPageComponent {

    strings: string[] = [];
    selectedSubject: string = '';
    grades: StudentGradeObject[] = [];
    form: FormGroup;
    formGrades: FormGroup;

    firstName: string = '';
    lastName: string = '';

    subject_names: { [key: number]: string } = {};

    constructor(public fb: FormBuilder, private http: HttpClient, private snackBar: MatSnackBar) {
        this.form = this.fb.group({
            username: [''],
            role: [''],
            subject: [''],
        });

        this.formGrades = this.fb.group({
            subject: [''],
        });
    }

    showSnackbar(content: string, action: string, duration: number) {
        this.snackBar.open(content, action, {
            duration: duration,
            panelClass: ['snackbar']
        });
    }

    validateNo(e: KeyboardEvent): boolean {
        const charCode = e.which ? e.which : e.keyCode;
        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
            return true
        }
        this.showSnackbar("Acest camp permite doar litere.", "Ok", 4000);
        return false
    }

    getGradesByMaterie() {
        this.selectedSubject = this.formGrades.get('subject')!.value;
        const params1 = new HttpParams().set('materie', this.selectedSubject);

        this.http.get<StudentGradeObject[]>('http://localhost:8082/Grades/getGradesByMaterie', {params: params1}).subscribe((data: StudentGradeObject[]) => {
            for (const item of data) {
                this.grades.push(item);
            }
        });
    }

    approveAll() {
        let i = 0;
        for (i = 0; i < this.grades.length; ++i) {
            const formData = new FormData();
            formData.append("firstName", this.grades[i].firstName);
            formData.append("lastName", this.grades[i].lastName);
            formData.append("materie", this.selectedSubject);

            this.http.put("http://localhost:8082/Grades/approvedGrades", formData).subscribe({
                next: (response) => {
                    console.log(response);

                },
                error: (error) => {
                    console.log(error);
                    this.showSnackbar('EROARE: Aprobarea nu a fost realizată cu succes.', 'Ok', 5000);
                },
            })

            this.showSnackbar('Toate notele sunt aprobate.', 'Ok', 5000);
        }
    }

    approveGrade(firstname: string, lastname: string) {
        const formData = new FormData();
        formData.append("firstName", firstname);
        formData.append("lastName", lastname);
        formData.append("materie", this.selectedSubject);

        this.http.put("http://localhost:8082/Grades/approvedGrades", formData).subscribe({
            next: (response) => {
                console.log(response);
                this.showSnackbar('Notă aprobată.', 'Ok', 5000);
            },
            error: (error) => {
                console.log(error);
                this.showSnackbar('EROARE: Aprobarea nu a fost realizată cu succes.', 'Ok', 5000);
            },
        })
    }

    ngOnInit() {
        this.http.get<string[]>('http://localhost:8082/class/getMaterie').subscribe(data => {
            this.strings = data;
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

        if (username != "" && subject != "") {
            formData.append('username', username);
            formData.append('subject', subject)

            let url = 'http://localhost:8082/class/';
            if (role == 'student') {
                url = url.concat('addStudent');
            } else {
                url = url.concat('addProfessorToClass');
            }

            // console.log(url);

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
