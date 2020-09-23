package controle;

import visao.JanelaPrincipal;

public class App {
	
	public static void main(String[] args) {
		
		JanelaPrincipal jan = new JanelaPrincipal();
		jan.setVisible(true);
		Controle controle = new Controle(jan);
		
	}

}
