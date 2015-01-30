package myprojects.coursera.android.capstone.mutibo;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import myprojects.coursera.android.capstone.mutibo.client.MutiboSvcApi;
import myprojects.coursera.android.capstone.mutibo.repository.QuizSet;
import myprojects.coursera.android.capstone.mutibo.repository.QuizSetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import retrofit.http.POST;
import retrofit.http.Path;

import com.google.common.collect.Lists;


// Tell Spring that this class is a Controller that should 
// handle certain HTTP requests for the DispatcherServlet
@Controller
public class QuizSetController {
	
	// The QuizSetRepository that we are going to store our quizSetRepo
	// in. We don't explicitly construct a QuizSetRepository, but
	// instead mark this object as a dependency that needs to be
	// injected by Spring. Our Application class has a method
	// annotated with @Bean that determines what object will end
	// up being injected into this member variable.
	//
	// Also notice that we don't even need a setter for Spring to
	// do the injection.
	//
	@Autowired
	private QuizSetRepository quizSetRepo;

	
	
	@RequestMapping(value=MutiboSvcApi.MOVIESET_SVC_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<QuizSet> getQuizSetList() {
		 
		List<QuizSet>  list;
		list = Lists.newArrayList(quizSetRepo.findAll()); 	// There are about 5000 sets! But it's pretty fast.
		Collections.shuffle(list);							// Shuffle the sets.
		return list.subList(1, 20);							// Then return the first 20 to the client.
//		return Lists.newArrayList(quizSetRepo.findAll());
	}

/*		
	@RequestMapping(value=MutiboSvcApi.MOVIESET_SVC_PATH + "/{id}", method=RequestMethod.GET)
	public @ResponseBody QuizSet getVideoById(@PathVariable("id") long id, HttpServletResponse response){
		QuizSet video = quizSetRepo.findOne(id) ;
		if (video == null){
			//throw new ResourceNotFoundException("video not found for id: " + id);
			response.setStatus(404);
		}
		return video;			
		
	}
*/
//	@RequestMapping(value=MutiboSvcApi.MOVIESET_SVC_PATH, method=RequestMethod.POST)
	//	public @ResponseBody QuizSet addVideo(@RequestBody QuizSet v){
	//		quizSetRepo.save(v);
	//	return v;
	//}
	

	@RequestMapping(value=MutiboSvcApi.MOVIESET_SVC_PATH + "/{id}/like", method=RequestMethod.POST)
	public void likeVideo(@PathVariable("id") long id, Principal p, HttpServletResponse response) {
		QuizSet quizSet = quizSetRepo.findOne(id) ;
		if (quizSet == null){
			response.setStatus(404);
			return;
		}
		
		//String username = p.getName(); 
		
		String username = "femmett";
		
		Set<String> likesUsernames = quizSet.getLikesUsernames();
		Set<String> disLikesUsernames = quizSet.getDisLikesUsernames();		
		
		// Checks if the user has already liked the video.
		if (likesUsernames.contains(username )) {
			response.setStatus(400);
		}else{
			//Store the user who has liked the video
			likesUsernames.add(username);
			quizSet.setLikesUsernames(likesUsernames);
			quizSet.setLikes(likesUsernames.size());
			quizSet.setLikes(likesUsernames.size() - disLikesUsernames.size());			
			quizSetRepo.save(quizSet);
			response.setStatus(200);
		}		  		
	}

	
	@RequestMapping(value=MutiboSvcApi.MOVIESET_SVC_PATH + "/{id}/dislike", method=RequestMethod.POST)	
	public void disLikeVideo(@PathVariable("id") long id, Principal p, HttpServletResponse response) {
		QuizSet quizSet = quizSetRepo.findOne(id) ;
		if (quizSet == null){
			response.setStatus(404);
			return;
		}
		
		//String username = p.getName(); 
		
		String username = "femmett";
		
		Set<String> disLikesUsernames = quizSet.getDisLikesUsernames();
		Set<String> likesUsernames = quizSet.getLikesUsernames();
		
		// Checks if the user has already liked the video.
		if (disLikesUsernames.contains(username )) {
			disLikesUsernames.remove(username);
			quizSet.setLikesUsernames(disLikesUsernames);
			quizSet.setLikes(likesUsernames.size() - disLikesUsernames.size());
			quizSetRepo.save(quizSet);
			response.setStatus(200);
		}else{
			response.setStatus(400);
		}
	}

	/*
	@RequestMapping(value=MutiboSvcApi.VIDEO_TITLE_SEARCH_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<QuizSet> findByTitle(
			// Tell Spring to use the "title" parameter in the HTTP request's query
			// string as the value for the title method parameter
			@RequestParam(MutiboSvcApi.TITLE_PARAMETER) String title
	){
		return quizSetRepo.findByName(title);
	}
	

	@RequestMapping(value=MutiboSvcApi.VIDEO_DURATION_SEARCH_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<QuizSet> findByDurationLessThan(
			// Tell Spring to use the "title" parameter in the HTTP request's query
			// string as the value for the title method parameter
			@RequestParam(MutiboSvcApi.DURATION_PARAMETER) long duration
	){
		return quizSetRepo.findByDurationLessThan(duration);
	}
*/
	@RequestMapping(value=MutiboSvcApi.MOVIESET_SVC_PATH + "/{id}/likedby", method=RequestMethod.GET)	
	public @ResponseBody Collection<String> getUsersWhoLikedVideo(@PathVariable("id") long id, HttpServletResponse response) {
		QuizSet video = quizSetRepo.findOne(id) ;
		if (video == null){
			response.setStatus(404);
		}
		
		Set<String> likesUsernames = video.getLikesUsernames();
		
		return likesUsernames;
	}



}
