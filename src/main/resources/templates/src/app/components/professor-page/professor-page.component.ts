import {Component} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
    selector: 'app-professor-page',
    templateUrl: './professor-page.component.html',
    styleUrls: ['./professor-page.component.css']
})
export class ProfessorPageComponent {
    expanded = false;

    subjects: string[] = [];
    students: string[] = [];
    form: FormGroup;

    constructor(public fb: FormBuilder, private http: HttpClient, private snackBar: MatSnackBar) {
        this.form = this.fb.group({
            firstName: [''],
            lastName:[''],
            materie: [''],
            grade: 0,
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
            this.subjects = data;
            console.log(this.subjects);
        });

        this.http.get<string[]>('http://localhost:8082/user/getListStudents').subscribe(data => {
            this.students = data;
            console.log(this.students);
        })
    }

    addGrade() {
        const formData: any = new FormData();

        const firstname = this.form.get('firstName')!.value;
        const lastname = this.form.get('lastName')!.value;
        const grade = this.form.get('grade')!.value;
        const subject = this.form.get('materie')!.value;

        console.log(firstname + " " + lastname + " " + subject + " " + grade);

        if (firstname != "" && subject != "") {
            formData.append('grade', grade);
            formData.append('firstName', grade);
            formData.append('lastName', grade);
            formData.append('materie', subject);

            let url = 'http://localhost:8082/Grades/addGrade';

            this.http.put(url, formData)
                .subscribe({
                    next: (response) => {
                        console.log(response);
                        this.showSnackbar("Nota " + grade + " acordată studentului " + firstname + " " + lastname, 'Ok', 5000);
                    },
                    error: (error) => {
                        console.log(error);
                        this.showSnackbar("EROARE: Studentul " + firstname + " " + lastname + " are deja notă la materia " + subject, 'Ok', 5000);
                    },
                });
        }
    }

    showCheckboxes() {
        const checkboxes = document.getElementById("checkboxes");
        if (checkboxes != null) {
            if (!this.expanded) {
                checkboxes.style.display = "none";
                this.expanded = true;
            } else {
                checkboxes.style.display = "block";
                this.expanded = false;
            }
        }
    }

}
