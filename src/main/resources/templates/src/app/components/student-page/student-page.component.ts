import {Component, OnInit} from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Calendar} from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import {FormBuilder, FormGroup} from "@angular/forms";
import {HttpClient} from "@angular/common/http";

@Component({
    selector: 'app-student-page',
    templateUrl: './student-page.component.html',
    styleUrls: ['./student-page.component.css']
})
export class StudentPageComponent implements OnInit {
    form: FormGroup;
    calendar!: Calendar;

    constructor(public fb: FormBuilder, private http: HttpClient, private snackBar: MatSnackBar) {
        this.form = this.fb.group({
        });
    }

    ngOnInit(): void {
        const calendarEl = document.getElementById('calendar');
        if (calendarEl) {
            this.calendar = new Calendar(calendarEl, {
                plugins: [dayGridPlugin],
                initialView: 'dayGridMonth',
                events: [
                    {
                        title: 'Event 1, Location',
                        date: '2023-05-01'
                    },
                    {
                        title: 'Event 2, Location 2',
                        date: '2023-05-01'
                    },
                    {
                        title: 'Event 2, Location 3',
                        date: '2023-06-24'
                    }
                ]
            });
            this.calendar.render();
        }
    }

    showSnackbar(content: string, action: string, duration: number) {
        this.snackBar.open(content, action, {
            duration: duration,
            verticalPosition: 'bottom',
            horizontalPosition: 'left',
            panelClass: ['snackbar']
        });
    }

    validateDate(e: KeyboardEvent): boolean {
        if (e.key == '/' || /^[0-9]$/i.test(e.key)) {
            return true;
        } else {
            this.showSnackbar("Data nasterii are urmatorul format: DD/MM/YYYY.", "Ok", 4000);
            return false
        }
    }

    validateNo(e: KeyboardEvent): boolean {
        const charCode = e.which ? e.which : e.keyCode;
        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
            this.showSnackbar("Acest camp permite doar cifre.", "Ok", 4000);
            return false
        }
        return true
    }

    updateInfo() {
        const formData: any = new FormData();

        formData.append('highSchool', this.form.get('studies')!.value);
        formData.append('room', this.form.get('room')!.value);
        formData.append('dorm', this.form.get('dorm')!.value);
        formData.append('phoneNumber', this.form.get('phone')!.value);
        formData.append('cnp', this.form.get('cnp')!.value);
        formData.append('birthday', this.form.get('birth')!.value);
        formData.append('iban', this.form.get('iban')!.value);

        let url = 'http://localhost:8082/user/putData';

        this.http.put(url, formData)
            .subscribe({
                next: (response) => {
                    console.log(response);
                    this.showSnackbar("Datele au fost actualizate cu succes.", 'Ok', 5000);
                },
                error: (error) => {
                    console.log(error);
                    this.showSnackbar("EROARE: Datele nu au putut fi actualizate", 'Ok', 5000);
                },
            });
    }
}
