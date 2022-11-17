package it.polito.tdp.ruzzle.model;

import java.util.ArrayList;
import java.util.List;

public class Ricerca {

	public List<Pos> torvaParola(String parola, Board board) {
		
		for(Pos p: board.getPositions()) {
			//cerchiamo la lettera in board uguale alla prima lettera di parola
			if(board.getCellValueProperty(p).get().charAt(0)==parola.charAt(0)) {
				//posso far partire la ricorsione
				List<Pos> parziale= new ArrayList<Pos>();
				parziale.add(p);
				if(cerca(parola, board, 1, parziale))
					return parziale;
			}	
		}
		return null;
	}
	
	
	public boolean cerca(String parola, Board board, int livello, List<Pos> percorso) {
		if(livello==parola.length()) {
			return true;
		}else {
			Pos ultima= percorso.get(percorso.size()-1);
			List<Pos> adiacenti= board.getAdjacencies(ultima);
			for(Pos a: adiacenti) {
				if(!percorso.contains(a) && board.getCellValueProperty(a).get().charAt(0)== parola.charAt(livello)) {
					percorso.add(a);
					//uscita rapida dalla ricorsione
					if(cerca(parola, board, livello+1, percorso))
						return true;
					
					//backtraking
					percorso.remove(percorso.size()-1);
				}
			}
			return false;
		}
	}
}
