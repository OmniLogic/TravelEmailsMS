package ai.omnilogic.travel.emails.repositories;

import ai.omnilogic.travel.emails.models.log.LogEmail;
import ai.omnilogic.travel.emails.models.log.ReservationEmailsHistory;
import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.azure.spring.data.cosmos.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogEmailRepository extends CosmosRepository<LogEmail, String> {
    @Query(value = "SELECT c.date, " +
            "c.email.to, " +
            "c.email.subject, " +
            "c.sent " +
            "FROM c WHERE " +
            "c.reserve_id = @reserve_id")
    List<ReservationEmailsHistory> findEmailByReserveId(@Param("reserve_id") String reserveId);

    @Query(value = "SELECT c.date, " +
            " c.email.to, " +
            " c.email.subject, " +
            " c.sent " +
            " FROM c WHERE " +
            " c.reserve_id = @reserve_id " +
            " and c.hotel_code = @hotel_code " +
            " and c.template in (\"emailOrderFailPix.ftl\", \"emailOrderFailPixAraxa.ftl\") ")
    List<ReservationEmailsHistory> findEmailByReserveIdWithoutEmailFail(@Param("hotel_code") Integer hotelCode,
                                                                        @Param("reserve_id") String reserveId);

    @Query(value = "SELECT * " +
            " FROM c\n " +
            " where array_contains(c.mandrill_id, @mandrill_id) " +
            " and (isnull(@hotel_code) ? true : c.hotel_code = @hotel_code) ")
    List<LogEmail> findByMandrillId(@Param("hotel_code") Integer hotelCode,
                                                    @Param("mandrill_id") String id);
}
