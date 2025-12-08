package com.Proyecto.backEnd.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.Proyecto.backEnd.dto.PersonalConLoginDTO;
import com.Proyecto.backEnd.model.PersonalModel;

public interface PersonalRepo extends JpaRepository<PersonalModel, Integer>, JpaSpecificationExecutor<PersonalModel> {

    @Query("""
                SELECT new com.Proyecto.backEnd.dto.PersonalConLoginDTO(
                    p.codp,
                    p.nombre,
                    p.ap,
                    p.am,
                    p.estado,
                    p.tipo,
                    p.foto,
                    u.login
                )
                FROM PersonalModel p
                LEFT JOIN p.usuarios u
            """)
    List<PersonalConLoginDTO> listarConLogin();
}
