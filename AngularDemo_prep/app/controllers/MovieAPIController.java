package controllers;

import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;
import models.*;

public class MovieAPIController extends Controller {
	
	public static Result get(){
		return ok(Movie.allAsJson());
	}
	
	public static Result create(){
		Form<Movie> movieForm = Form.form(Movie.class).bindFromRequest();
		if(movieForm.hasErrors()){
			return badRequest(movieForm.errorsAsJson());
		}
		
		Movie movie = movieForm.get();
		if(movie.id <= 0){
			movie = Movie.create(movie);
		} else {
			movie = Movie.edit(movie);
		}
		
		return ok(movie.toJson());
	}
	
	public static Result delete(int id){
		Movie.delete(id);
		return ok();
	}

}
