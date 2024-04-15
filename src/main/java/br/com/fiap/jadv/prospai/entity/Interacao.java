package br.com.fiap.jadv.prospai.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "interacao")
public class Interacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "interacao_seq")
    @SequenceGenerator(name = "interacao_seq", sequenceName = "interacao_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    @ToString.Exclude
    private Cliente cliente;

    @Column(name = "data_interacao", nullable = false)
    private Date dataInteracao;

    @Column(name = "descricao", nullable = false, length = 1000)
    private String descricao;

    // Getters e setters são gerados automaticamente pelo Lombok

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Interacao interacao = (Interacao) o;
        return getId() != null && Objects.equals(getId(), interacao.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
