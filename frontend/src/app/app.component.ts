
import { Component, ElementRef, ViewChild, ChangeDetectorRef } from '@angular/core';
import { HttpClient, HttpEventType, HttpHeaders } from '@angular/common/http';
import { FileUploader } from 'ng2-file-upload';

interface Book { id: number; name: string; content?: string }


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontendClone';
  fileData: File = null;
  constructor(private http: HttpClient, private changeDetection: ChangeDetectorRef) { }
  fileUploaded = ''

  @ViewChild('fileInput', { static: true }) fileInput: ElementRef;

  uploader: FileUploader;
  isDropOver: boolean;
  allBooks: any = []
  bookName = ''

  ngOnInit(): void {

    const headers = [{ name: 'Accept', value: 'application/json', crossDomain: true }];
    this.uploader = new FileUploader({ url: 'http://localhost:9090/upload', autoUpload: true, headers });
    this.uploader.onBeforeUploadItem = (item) => {
      item.withCredentials = false;
    };
    this.uploader.onCompleteAll = () => {
      console.log('File: ' + this.uploader.queue[0].file.name + ' - Uploaded Successfully')
      this.fileUploaded = 'File: ' + this.uploader.queue[0].file.name + ' - Uploaded Successfully'
      this.findall()
    }
  }

  fileOverAnother(e: any): void {
    this.isDropOver = e;
  }

  fileClicked() {
    this.fileInput.nativeElement.click();
  }


  createBook() {
    this.http.post('http://localhost:9090/book', { name: this.bookName }).subscribe((res) => {
      // Call the get request to check if a book has been created.
      this.findall()
      console.log('Created a customer');
      console.log(res);
    });
  }

  deleteAll() {
    this.http.get('http://localhost:9090/deleteAll').subscribe((res) => {
      // Call the get request to check if all books has been deleted.
      this.findall()
      console.log('Created a customer');
      console.log(res);
    });
  }

  findall() {
    this.http.get('http://localhost:9090/findall').subscribe((res: Book[]) => {
      this.AllBooks = res.sort((a, b) => a.id - b.id)
      console.log('finall books');
      console.log(res);
    });
  }

  get AllBooks() {
    return this.allBooks
  }

  set AllBooks(value) {
    this.allBooks = value
  }

}

