package com.ovegam.engine.pfmegrnargs.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.lang.Object;
import java.lang.String;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(
    name = "ovegam"
)
public class Aws41ApiController {
  @Autowired
  private NamedParameterJdbcTemplate namedJdbcTemplate;

  @GetMapping("/api/v1/aws41-api")
  public ResponseEntity<List<Map<String, Object>>> aws41_api(@RequestParam String param1,
      @RequestParam String param2) {
    MapSqlParameterSource paramSource= new MapSqlParameterSource();
    paramSource.addValue("param1",param1);
    paramSource.addValue("param2",param2);
    return ResponseEntity.ok(namedJdbcTemplate.queryForList("SELECT  upi,  taxid,  ac FROM xref WHERE ac IN (:param1,:param2)",paramSource));
  }
}
