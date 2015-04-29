package controllers;


import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;
import models.*;

public class MovieController extends Controller {

	
	
	public static Result index(){
		return ok(movieIndex.render(Movie.all()));
	}
	
	public static Result add(){
		return ok(movieAdd.render(new Form<Movie>(Movie.class)));
	}
	
	
	public static Result create(){
		Form<Movie> movieForm = Form.form(Movie.class).bindFromRequest();
		if(movieForm.hasErrors()){
			return badRequest(movieAdd.render(movieForm));
		}
		Movie m = movieForm.get();
		Movie.create(m);
		return redirect(routes.MovieController.index());
	}
	
	public static Result update(int id){
		Movie movie = Movie.get(id);
		return ok(movieUpdate.render(new Form<Movie>(Movie.class).fill(movie)));
	}
	
	
	public static Result edit(){
		Form<Movie> movieForm = Form.form(Movie.class).bindFromRequest();
		if(movieForm.hasErrors()){
			return badRequest(movieUpdate.render(movieForm));
		}
		Movie movie = movieForm.get();
		return redirect(routes.MovieController.index());
	}
	
	
	public static Result delete(int id){
		Movie.delete(id);
		return redirect(routes.MovieController.index());
	}
	
	
	
}
