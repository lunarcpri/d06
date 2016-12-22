package repositories;

import domain.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Integer> {

    @Query("select f from Folder f where f.actor.id = ?1 and f.folderType='INBOX'")
    Folder findInboxFolderByActorId(int id);

    @Query("select f from Folder f where f.actor.id = ?1 and f.folderType='SPAMBOX'")
    Folder findSpamboxFolderByActorId(int id);

    @Query("select f from Folder f where f.actor.id = ?1 and f.folderType='OUTBOX'")
    Folder findOutboxFolderByActorId(int id);

    @Query("select f from Folder f where f.actor.id = ?1 and f.folderType='THRASHBOX'")
    Folder findTrashboxFolderByActorId(int id);


    @Query("select f from Folder f join f.messages m where m.id=?2 and f.actor.id=?1")
    Folder findFolderByMessageAndActor(int actorid, int messageid);

}
