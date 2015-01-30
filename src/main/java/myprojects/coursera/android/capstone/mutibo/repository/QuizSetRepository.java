package myprojects.coursera.android.capstone.mutibo.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * An interface for a repository that can store QuizSet
 * objects and allow them to be searched by title.
 * 
 * @author jules
 *
 */
@Repository
public interface QuizSetRepository extends CrudRepository<QuizSet, Long>{

	// Find all videos with a matching title (e.g., QuizSet.name)
//	public Collection<QuizSet> findByName(String title);

	public Collection<QuizSet> findByCombinationLessThan(long combination);
	
}
