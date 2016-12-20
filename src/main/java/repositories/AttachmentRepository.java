package repositories;

import domain.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends
		JpaRepository<Attachment, Integer> {

}
