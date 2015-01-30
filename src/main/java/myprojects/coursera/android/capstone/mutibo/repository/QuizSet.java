package myprojects.coursera.android.capstone.mutibo.repository;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.common.base.Objects;

/**
 * A simple object to represent a video and its URL for viewing.
 * 
 * You probably need to, at a minimum, add some annotations to this
 * class.
 * 
 * You are free to add annotations, members, and methods to this
 * class. However, you probably should not change the existing
 * methods or member variables. If you do change them, you need
 * to make sure that they are serialized into JSON in a way that
 * matches what is expected by the auto-grader.
 * 
 * @author mitchell
 */

@Entity
public class QuizSet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long combination;	

	private String media_Type;	
	private String question_Type;
	private String common_Attribute;
	private String common_Relationship_Text;	
	private String title1;
	private String title2;
	private String title3;	
	private String odd_Title;
	private String odd_Attribute;
	private String odd_Relationship_Text;
	
	private long likes;
	
	@ElementCollection
	private Set<String> likesUsernames = new HashSet<String>();
	
	@ElementCollection
	private Set<String> disLikesUsernames = new HashSet<String>();
	
	
	public QuizSet() {
	}

	public QuizSet(	
					String media_Type,
					String question_Type,
					String common_Attribute,
					String common_Relationship_Text,	
					String title1,
					String title2,
					String title3,	
					String odd_Title,
					String odd_Attribute,
					String odd_Relationship_Text,
					long likes
					) {
		super();
		this.media_Type = media_Type;
		this.question_Type = question_Type;
		this.common_Attribute = common_Attribute;
		this.common_Relationship_Text = common_Relationship_Text;	
		this.title1 = title1;
		this.title2 = title2;
		this.title3 = title3;	
		this.odd_Title = odd_Title;
		this.odd_Attribute = odd_Attribute;
		this.odd_Relationship_Text = odd_Relationship_Text;		
		this.likes = likes;				
	}
	
	public String getOdd_Attribute() {
		return odd_Attribute;
	}

	public void setOdd_Attribute(String odd_Attribute) {
		this.odd_Attribute = odd_Attribute;
	}

	public String getMedia_Type() {
		return media_Type;
	}
	
	public long getCombination() {
		return combination;
	}

	public String getQuestion_Type() {
		return question_Type;
	}

	public String getCommon_Attribute() {
		return common_Attribute;
	}

	public String getCommon_Relationship_Text() {
		return common_Relationship_Text;
	}

	public String getTitle1() {
		return title1;
	}

	public String getTitle2() {
		return title2;
	}

	public String getTitle3() {
		return title3;
	}

	public String getOdd_Title() {
		return odd_Title;
	}

	public String getOdd_Relationship_Text() {
		return odd_Relationship_Text;
	}
	
	public long getLikes() {
		return likes;
	}

	public void setLikes(long likes) {
		this.likes = likes;
	}
	
	/**
	 * Two Videos will generate the same hashcode if they have exactly the same
	 * values for their name, url, and duration.
	 * 
	 */
//	@Override
//		public int hashCode() {
		// Google Guava provides great utilities for hashing
//		return Objects.hashCode(name, url, duration);
//	}

	/**
	 * Two sets are considered equal if they have exactly the same values for
	 * title1
	 * title2
	 * title3 
	 * common_Attribute
	 * odd_Title
	 * odd_Relationship_Text
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof QuizSet) {
			QuizSet other = (QuizSet) obj;
			// Google Guava provides great utilities for equals too!
			return Objects.equal(title1, other.title1)
					&& Objects.equal(title2, other.title2)
					&& Objects.equal(title3, other.title3)
					&& Objects.equal(common_Attribute, other.common_Attribute)
					&& Objects.equal(odd_Title, other.odd_Title)					
					&& odd_Relationship_Text == other.odd_Relationship_Text;
		} else {
			return false;
		}
	}

	public Set<String> getLikesUsernames() {
	  return likesUsernames;
	}

	public Set<String> getDisLikesUsernames() {
		  return disLikesUsernames;
		}
	
	public void setLikesUsernames(Set<String> likesUsernames) {
	  this.likesUsernames = likesUsernames;
	}

	public void setDisLikesUsernames(Set<String> disLikesUsernames) {
		  this.disLikesUsernames = disLikesUsernames;
	}	
	
}
