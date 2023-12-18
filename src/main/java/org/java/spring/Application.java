package org.java.spring;

import org.java.spring.auth.db.conf.AuthConf;
import org.java.spring.auth.db.pojo.Role;
import org.java.spring.auth.db.pojo.User;
import org.java.spring.auth.db.service.RoleService;
import org.java.spring.auth.db.service.UserService;
import org.java.spring.pojo.Category;
import org.java.spring.pojo.Message;
import org.java.spring.pojo.Photo;
import org.java.spring.service.CategoryService;
import org.java.spring.service.MessageService;
import org.java.spring.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner{
	
	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageService messageService;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category("Ritratti");
		Category cat2 = new Category("Paesaggi");
		Category cat3 = new Category("Architettura");
		Category cat4 = new Category("Street Photography");
		Category cat5 = new Category("Natura Morta");
		Category cat6 = new Category("Macrofotografia");
		Category cat7 = new Category("Fotografia Astronomica");
		Category cat8 = new Category("Fotografia Documentaristica");
		Category cat9 = new Category("Fotografia di Moda");
		Category cat10 = new Category("Fotografia Culinarie");
		
		categoryService.save(cat1);
		categoryService.save(cat2);
		categoryService.save(cat3);
		categoryService.save(cat4);
		categoryService.save(cat5);
		categoryService.save(cat6);
		categoryService.save(cat7);
		categoryService.save(cat8);
		categoryService.save(cat9);
		categoryService.save(cat10);
		
		
		
		Role roleAdmin = new Role("ADMIN");
		Role superAdm = new Role("SUPERADMIN");
		roleService.save(superAdm);
		roleService.save(roleAdmin);
		
		String pws = AuthConf.passwordEncoder().encode("pws");
		
		User admin1 = new User("mignolo", pws, roleAdmin);
		User admin2 = new User("mignolo2", pws, roleAdmin);
		User superAdmin = new User("mignoloAdm", pws, superAdm);
		
		userService.save(superAdmin);
		userService.save(admin1);
		userService.save(admin2);
		
		
		Photo ph1 = new Photo("albero morente", "La foto ritrae un albero morente avvolto in un'atmosfera di malinconia e decadimento. Le foglie, un tempo rigogliose e vibranti, ora appaiono disseccate e ingiallite, pronte a lasciare definitivamente i rami spogli. La corteccia mostra segni di deperimento, creando un contrasto visivo tra la forza della natura e la sua inevitabile fragilità.", "https://images.unsplash.com/photo-1610621062045-ef5f7201bb3f?q=80&w=1000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8ZmluZSUyMGFydCUyMHBob3RvZ3JhcGh5fGVufDB8fDB8fHww", false,admin2, false, cat1, cat3, cat6);
		Photo ph2 = new Photo("uomo con valigia", " La valigia, logora ma carica di significato, poggia sulla superficie, testimone di viaggi passati o forse pronta per nuove avventure. La scena cattura un momento di transizione e di attesa, lasciando spazio a molteplici interpretazioni sulla storia e sul destino di questo viaggiatore. ", "https://shotkit.com/wp-content/uploads/2021/03/fine-art-photography.jpg", false, admin1 , false, cat5,cat8,cat10);
		Photo ph3 = new Photo("zebra", "La foto cattura l'essenza affascinante di una zebra in bianco e nero, mostrando la sua bellezza unica nella natura. La pelliccia striata di bianco e nero è chiaramente visibile, creando un motivo distintivo che si snoda in un armonioso contrasto.", "https://assets-global.website-files.com/60e4d0d0155e62117f4faef3/621d0551187b0816a87b197b_01-vortex.jpeg", false, admin1,false, cat4, cat7,cat3,cat9);
		Photo ph4 = new Photo("terra e mare", "La fotografia cattura la serenità e l'isolamento di una scena marina in bianco e nero. La spiaggia si estende deserta, priva di presenze umane, creando un senso di tranquillità e pace. Le onde del mare lambiscono delicatamente la riva, lasciando impronte sabbiose sulla superficie della spiaggia.", "https://m.media-amazon.com/images/I/71JOXVVYfEL._UF894,1000_QL80_.jpg", false,admin2,false, cat5,cat8,cat3,cat4,cat1);
		Photo ph5 = new Photo("donna con violoncello", "Armonia in musica: una donna appassionata suona il violoncello con grazia e intensità", "https://www.viewbug.com/media/mediafiles/2015/01/24/43948701_large1300.jpg", false,admin1,false, cat3,cat8);
		Photo ph6 = new Photo("fotocamera", "Catturando il momento: la potenza dietro l'obiettivo, la fotocamera, testimone silente di storie e emozioni", "https://www.textileartist.org/wp-content/uploads/2014/05/Featured.png", false,admin1,false, cat3,cat8, cat7, cat9);
		Photo ph7 = new Photo("rudere", "Memorie dimenticate: un rudere che narra storie del passato, testimone silenzioso del trascorrere del tempo.", "https://i0.wp.com/digital-photography-school.com/wp-content/uploads/2015/06/fine-art-photography0005.jpg?fit=750%2C495&ssl=1", false,admin1,false, cat3,cat2, cat4);
		Photo ph8 = new Photo("nebbia", "Tra le brume del mistero: un paesaggio avvolto nella nebbia, rivelando solo contorni sfumati e un'atmosfera magica.", "https://cdn.fstoppers.com/styles/large-16-9/s3/lead/2023/08/fine-art-15.jpg", false,admin1,false, cat1,cat5);
		Photo ph9 = new Photo("coppia", "Complicità e connessione: una coppia seduta insieme, con sguardi che parlano più di mille parole", "https://www.juliaannagospodarou.com/wp-content/uploads/2015/07/MK3_8267_1600px_website.jpg", false,admin2,false, cat3,cat7,cat6,cat5);
		Photo ph10 = new Photo("lupo", "Forza e legame: una donna con un lupo sulle spalle, simbolo di forza selvaggia e connessione con la natura.", "https://d11kvfv4kxw5s4.cloudfront.net/wp-content/uploads/sites/15/2021/07/14132416/desktop-_-new-website_12.07.2021sn-photography.jpg", false,admin2,false, cat9, cat6,cat5);
		Photo ph11= new Photo("anziano in vietnam", "Vita vissuta: il volto di un anziano vietnamita, ricco di esperienze e saggezza, testimone della storia e della cultura.", "https://www.rehahnphotographer.com/wp-content/uploads/2021/06/rehahn-vietnam-portrait-fine-art-photography-cham-ethnic.jpg", false,admin2,false, cat1);
		Photo ph12 = new Photo("danza", "Armonia in movimento: una coppia che danza, esprimendo gioia, passione e intimità attraverso il linguaggio del corpo.", "https://cdn.openart.ai/stable_diffusion/3338c1bfb8e57a98621772c2fcfdcd870b94e31e_2000x2000.webp", false,admin1,false, cat2,cat9,cat3);

		
		photoService.save(ph1);
		photoService.save(ph2);
		photoService.save(ph3);
		photoService.save(ph4);
		photoService.save(ph5);
		photoService.save(ph6);
		photoService.save(ph7);
		photoService.save(ph8);
		photoService.save(ph9);
		photoService.save(ph10);
		photoService.save(ph11);
		photoService.save(ph12);
		
		Message m1 = new Message("franco-rossi@msn.com", "ciao vorrei iscrivermi alla piattaforma! ", false);
		Message m2 = new Message("LiberoNonno@yahoo.com", "salve, vorrei sapere i prezzi del gestionale", false);
		Message m3 = new Message("Gianmai@yahoo.com", "salve, vorrei postare alcune foto", false);
		Message m4 = new Message("MarioMerola@msn.com", "salve, come posso condividere un account?", false);

		messageService.save(m1);
		messageService.save(m2);
		messageService.save(m3);
		messageService.save(m4);
		

	}

}
