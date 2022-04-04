import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';


export abstract class RESTDAOSecurityService<T, K> {

  protected securityUrl = environment.usuariosApiURL;

  constructor(protected http: HttpClient, entidad: string, protected option = {}) {
    this.securityUrl += entidad;
  }
  query(): Observable<Array<T>> {
    return this.http.get<Array<T>>(this.securityUrl, this.option);
  }
  get(id: K): Observable<T> {
    return this.http.get<T>(this.securityUrl + '/' + id, this.option);
  }
  add(item: T): Observable<T> {
    return this.http.post<T>(this.securityUrl, item, this.option);
  }
  change(id: K, item: T): Observable<T> {
    return this.http.put<T>(this.securityUrl + '/' + id, item, this.option);
  }
  remove(id: K): Observable<T> {
    return this.http.delete<T>(this.securityUrl + '/' + id, this.option);
  }

}
