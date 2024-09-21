package br.ricardo.suport.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Utilização do Lombok
@JsonIgnoreProperties(ignoreUnknown = true) //config do Jackson para dizer para deconsiderar propriedades desconhecidas como id e refreshToken
@Data// Serve para gerar Getter e Setter juntos do Lombok(Lombok serve para reduzir as linhas e não criar o Pojo padrão antigo)
@AllArgsConstructor
@NoArgsConstructor
@Builder //com Builder, se não passar nada na chamada, será utilizado os dados inicializados no builder
public class User {
    private String token;
    private String refreshToken;
    private Integer id;
    @Builder.Default
    private String username = "emilys";
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    @Builder.Default
    private String password = "emilyspass";
    @Builder.Default
    private Integer expiresInMins = 30;


    public User(String username, String password, Integer expiresInMins) {
        super();
        this.username = username;
        this.password = password;
        this.expiresInMins = expiresInMins;
    }

}
