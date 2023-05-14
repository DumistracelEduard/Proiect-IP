import {Component} from '@angular/core';
import {MatSnackBar} from "@angular/material/snack-bar";
import {OnInit } from '@angular/core';

declare var calendar: any;

@Component({
    selector: 'app-student-page',
    templateUrl: './student-page.component.html',
    styleUrls: ['./student-page.component.css']
})

export class StudentPageComponent implements OnInit{
  
    ngOnInit(): void {
      new calendar();
    }
    
    title = 'app-js';

    constructor(private snackBar: MatSnackBar) {
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
  }
