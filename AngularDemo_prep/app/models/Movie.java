package models;

import java.util.List;

import javax.persistence.Entity;

import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;

import javax.persistence.*;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.db.ebean.Model;
import play.libs.Json;

@Entity
public class Movie extends Model {

	@Id
	public int id;

	@Required
	@MinLength(2)
	public String title;

	@Required
	@MinLength(2)
	public String director;

	private static Finder<Integer, Movie> find = new Finder<Integer, Movie>(
			Integer.class, Movie.class);

	public static Movie create(Movie movie) {
		movie.save();
		return movie;
	}

	public static Movie edit(Movie movie) {
		if (movie.id > 0) {
			Movie original = find.byId(movie.id);
			original.title = movie.title;
			original.director = movie.director;
			original.update();
			return original;
		} else {
			return create(movie);
		}
	}

	public static void delete(int id) {

		find.byId(id).delete();
	}

	public static Movie get(int id) {
		return find.byId(id);
	}

	public static List<Movie> all() {
		return find.all();
	}
	
	public static String allAsJson(){
		List<Movie> all = find.all();
		ArrayNode result = new ArrayNode(null);
		for(Movie movie : all){
			ObjectNode movieNode = Json.newObject();
			movieNode.put("id", movie.id);
			movieNode.put("title", movie.title);
			movieNode.put("director", movie.director);
			result.add(movieNode);
		}
		return result.toString();
	}

	public String toJson() {
		ObjectNode result = Json.newObject();
		result.put("id", this.id);
		result.put("title", this.title);
		result.put("director", this.director);
		return result.toString();
	}

}
