package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.pl.NIP;

@Entity
@Table(name = "dosen")
public class Dosen {
	private String nama;
	private long id;
	private String alamat;
	
	
	public Dosen() {
	
}
	public Dosen(String nama,String alamat) {
		this.nama=nama;
		this.alamat=alamat;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id= id;
	}
	@Column(name="nama",nullable=false)
	public String getNama() {
		return nama;
	}
	public static void setNama(String nama) {
		nama=nama;
	}
	@Column(name="alamat",nullable=false)
		public String getAlamat() {
		return alamat;
	}
	public static void setAlamat(String alamat) {
		alamat=alamat;
	}
	
	public String topString() {
		return "Dosen[id="+ id+",nama="+nama+",alamat="+alamat+"]";
	}
	
	
	
	
	
	
	
	
	
	
	
}
