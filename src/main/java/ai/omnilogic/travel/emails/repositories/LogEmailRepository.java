package ai.omnilogic.travel.emails.repositories;

import ai.omnilogic.travel.emails.models.log.LogEmail;
import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.azure.spring.data.cosmos.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogEmailRepository extends CosmosRepository<LogEmail, String> {

    @Query(value = "SELECT * " +
            " FROM c\n " +
            " where array_contains(c.mandrill_id, @mandrill_id) " +
            " and (isnull(@hotel_code) ? true : c.hotel_code = @hotel_code) ")
    List<LogEmail> findByMandrillId(@Param("hotel_code") Integer hotelCode,
                                                    @Param("mandrill_id") String id);
}
