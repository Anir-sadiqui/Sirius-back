package Episante.back.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICrudRepository<T, ID> extends JpaRepository<T, ID> {

}
