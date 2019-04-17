package features.rest;


import io.restassured.filter.session.SessionFilter;
import io.restassured.http.Cookies;

public class RestSessionObject {


    //Can be used to hold session data for few sequence requests
    private Cookies cookies;
    private String csrfId;
    private SessionFilter sessionFilter;


    public RestSessionObject() {
        this.sessionFilter = new SessionFilter();
    }

    public Cookies getCookies() {
        return cookies;
    }

    public void setCookies(Cookies cookies) {
        this.cookies = cookies;
    }

    public String getCsrfId() {
        return csrfId;
    }

    public void setCsrfId(String csrfId) {
        this.csrfId = csrfId;
    }

    public SessionFilter getSessionFilter() {
        return sessionFilter;
    }
}
