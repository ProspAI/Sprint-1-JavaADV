package br.com.fiap.jadv.prospai.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "compra_seq")
    @SequenceGenerator(name = "compra_seq", sequenceName = "compra_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    @ToString.Exclude
    private Cliente cliente;

    @Column(name = "produto", nullable = false)
    private String produto;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Column(name = "data_compra", nullable = false)
    private Date dataCompra;

	public void setDataCompra() {
		// TODO Auto-generated method stub
		
	}

    // Getters e setters são gerados automaticamente pelo Lombok

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Compra compra = (Compra) o;
        return getId() != null && Objects.equals(getId(), compra.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}

