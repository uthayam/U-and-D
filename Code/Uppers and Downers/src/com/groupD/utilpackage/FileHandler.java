package com.groupD.utilpackage;

/**
 *
 * @author mesfin
 */

import javax.xml.parsers.*;
import java.io.*;
import java.util.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import com.groupD.corepackage.*;

public class FileHandler
{
	private static Document gamedoc; 
	private static boolean parsed = false;
	private static TreeMap<String,Game> gamemap = new TreeMap<String,Game>();
	
	public static TreeMap<String,Game> getAllGames()
	{
		if(!parsed)
		{
			parse();
		}
		return gamemap;
	}
	
        /**
         * This method returns a game by the given id from the gamelist file.
         * @param id
         * @return Game
         */
	public static Game getGame(String id)
	{
		if(!parsed)
		{
			parse();
		}
		return gamemap.get(id);
	}
	
	public static void addGame(Game g)
	{
		if(!parsed)
		{
			parse();
		}
		
		try
		{
			Node gamelist = gamedoc.createElement("GAMELIST");
			gamelist = gamedoc.getElementsByTagName("GAMELIST").item(0);
		
			Element game = gamedoc.createElement("GAME");
			Attr id = gamedoc.createAttribute("id");
			id.setValue(g.getId());
			game.setAttributeNode(id);
		
			Element die = gamedoc.createElement("DIE");
			Attr nr = gamedoc.createAttribute("numberRolled");
			nr.setValue(""+g.getDie().getNumberRolled());
			die.setAttributeNode(nr);
			game.appendChild(die);
		
			Element players = gamedoc.createElement("PLAYERS");
		
			Element player1 = gamedoc.createElement("PLAYER");
			Attr name1 = gamedoc.createAttribute("name");
			name1.setValue(g.getPlayer1().getName());
			player1.setAttributeNode(name1);
			Attr type1 = gamedoc.createAttribute("type");
			type1.setValue((g.getPlayer1() instanceof HumanPlayer) ? "h" : "c");
			player1.setAttributeNode(type1);
		
			Element token1 = gamedoc.createElement("TOKEN");
			Attr color1 = gamedoc.createAttribute("color");
			color1.setValue(g.getPlayer1().getToken().getColor());
			token1.setAttributeNode(color1);
			Attr position1 = gamedoc.createAttribute("position");
			position1.setValue(""+g.getPlayer1().getToken().getPosition());
			token1.setAttributeNode(position1);
		
			Element total1 = gamedoc.createElement("TOTALSCORE");
			Attr tsvalue1 = gamedoc.createAttribute("value");
			tsvalue1.setValue(""+g.getPlayer1().getTotalScore());
			total1.setAttributeNode(tsvalue1);
		
			Element highest1 = gamedoc.createElement("HIGHESTSCORE");
			Attr hsvalue1 = gamedoc.createAttribute("value");
			hsvalue1.setValue(""+g.getPlayer1().getHighestScore());
			highest1.setAttributeNode(hsvalue1);
		
			Element gplayed1 = gamedoc.createElement("GAMESPLAYED");
			Attr gpvalue1 = gamedoc.createAttribute("value");
			gpvalue1.setValue(""+g.getPlayer1().getGamesPlayed());
			gplayed1.setAttributeNode(gpvalue1);
		
			Element gwon1 = gamedoc.createElement("GAMESWON");
			Attr gwvalue1 = gamedoc.createAttribute("value");
			gwvalue1.setValue(""+g.getPlayer1().getGamesWon());
			gwon1.setAttributeNode(gwvalue1);
		
			player1.appendChild(token1);
			player1.appendChild(total1);
			player1.appendChild(highest1);
			player1.appendChild(gplayed1);
			player1.appendChild(gwon1);
		
			Element player2 = gamedoc.createElement("PLAYER");
			Attr name2 = gamedoc.createAttribute("name");
			name2.setValue(g.getPlayer2().getName());
			player2.setAttributeNode(name2);
			Attr type2 = gamedoc.createAttribute("type");
			type2.setValue((g.getPlayer2() instanceof HumanPlayer) ? "h" : "c");
			player2.setAttributeNode(type2);
		
			Element token2 = gamedoc.createElement("TOKEN");
			Attr color2 = gamedoc.createAttribute("color");
			color2.setValue(g.getPlayer2().getToken().getColor());
			token2.setAttributeNode(color2);
			Attr position2 = gamedoc.createAttribute("position");
			position2.setValue(""+g.getPlayer2().getToken().getPosition());
			token2.setAttributeNode(position2);
		
			Element total2 = gamedoc.createElement("TOTALSCORE");
			Attr tsvalue2 = gamedoc.createAttribute("value");
			tsvalue2.setValue(""+g.getPlayer2().getTotalScore());
			total2.setAttributeNode(tsvalue2);
		
			Element highest2 = gamedoc.createElement("HIGHESTSCORE");
			Attr hsvalue2 = gamedoc.createAttribute("value");
			hsvalue2.setValue(""+g.getPlayer2().getHighestScore());
			highest2.setAttributeNode(hsvalue2);
		
			Element gplayed2 = gamedoc.createElement("GAMESPLAYED");
			Attr gpvalue2 = gamedoc.createAttribute("value");
			gpvalue2.setValue(""+g.getPlayer2().getGamesPlayed());
			gplayed2.setAttributeNode(gpvalue2);
		
			Element gwon2 = gamedoc.createElement("GAMESWON");
			Attr gwvalue2 = gamedoc.createAttribute("value");
			gwvalue2.setValue(""+g.getPlayer2().getGamesWon());
			gwon2.setAttributeNode(gwvalue2);
		
			player2.appendChild(token2);
			player2.appendChild(total2);
			player2.appendChild(highest2);
			player2.appendChild(gplayed2);
			player2.appendChild(gwon2);
		
			players.appendChild(player1);
			players.appendChild(player2);
		
			game.appendChild(players);
		
			Element board = gamedoc.createElement("BOARD");
			Attr rows = gamedoc.createAttribute("rows");
			rows.setValue(""+g.getBoard().getRows());
			board.setAttributeNode(rows);
			Attr columns = gamedoc.createAttribute("columns");
			columns.setValue(""+g.getBoard().getColumns());
			board.setAttributeNode(columns);
		
			Element uppers = gamedoc.createElement("UPPERS");
		
			int[] upperpos = g.getBoard().getUppers();
			int[] upperpoints = g.getBoard().getUpperScores();
			for(int i=0; i<upperpos.length; i++)
			{
				Element upper = gamedoc.createElement("UPPER");
				Attr pos = gamedoc.createAttribute("position");
				pos.setValue(""+upperpos[i]);
				upper.setAttributeNode(pos);
				Attr des = gamedoc.createAttribute("destination");
				des.setValue(""+g.getBoard().getCell(upperpos[i]).getCellNumber());
				upper.setAttributeNode(des);
				Attr point = gamedoc.createAttribute("point");
				point.setValue(""+upperpoints[i]);
				upper.setAttributeNode(point);
				uppers.appendChild(upper);
			}
		
			Element downers = gamedoc.createElement("DOWNERS");
		
			int[] downerpos = g.getBoard().getDowners();
			int[] downerpoints = g.getBoard().getDownerScores();
			for(int i=0; i<downerpos.length; i++)
			{
				Element downer = gamedoc.createElement("DOWNER");
				Attr pos = gamedoc.createAttribute("position");
				pos.setValue(""+downerpos[i]);
				downer.setAttributeNode(pos);
				Attr des = gamedoc.createAttribute("destination");
				des.setValue(""+g.getBoard().getCell(downerpos[i]).getCellNumber());
				downer.setAttributeNode(des);
				Attr point = gamedoc.createAttribute("point");
				point.setValue(""+downerpoints[i]);
				downer.setAttributeNode(point);
				downers.appendChild(downer);
			}
		
			Element bonuses = gamedoc.createElement("BONUSES");
		
			int[] bonuspos = g.getBoard().getBonuses();
			int[] bonuspoints = g.getBoard().getBonusScores();
			for(int i=0; i<bonuspos.length; i++)
			{
				Element bonus = gamedoc.createElement("BONUS");
				Attr pos = gamedoc.createAttribute("position");
				pos.setValue(""+bonuspos[i]);
				bonus.setAttributeNode(pos);
				Attr point = gamedoc.createAttribute("point");
				point.setValue(""+bonuspoints[i]);
				bonus.setAttributeNode(point);
				bonuses.appendChild(bonus);
			}
		
			board.appendChild(uppers);
			board.appendChild(downers);
			board.appendChild(bonuses);
		
			game.appendChild(board);
		
			Element turn = gamedoc.createElement("TURN");
		
			Element player3 = gamedoc.createElement("PLAYER");
			Attr name3 = gamedoc.createAttribute("name");
			name3.setValue(g.getTurn().getName());
			player3.setAttributeNode(name3);
			Attr type3 = gamedoc.createAttribute("type");
			type3.setValue((g.getTurn() instanceof HumanPlayer) ? "h" : "c");
			player3.setAttributeNode(type3);
		
			Element token3 = gamedoc.createElement("TOKEN");
			Attr color3 = gamedoc.createAttribute("color");
			color3.setValue(g.getTurn().getToken().getColor());
			token3.setAttributeNode(color3);
			Attr position3 = gamedoc.createAttribute("position");
			position3.setValue(""+g.getTurn().getToken().getPosition());
			token3.setAttributeNode(position3);
		
			Element total3 = gamedoc.createElement("TOTALSCORE");
			Attr tsvalue3 = gamedoc.createAttribute("value");
			tsvalue3.setValue(""+g.getTurn().getTotalScore());
			total3.setAttributeNode(tsvalue3);
		
			Element highest3 = gamedoc.createElement("HIGHESTSCORE");
			Attr hsvalue3 = gamedoc.createAttribute("value");
			hsvalue3.setValue(""+g.getTurn().getHighestScore());
			highest3.setAttributeNode(hsvalue3);
		
			Element gplayed3 = gamedoc.createElement("GAMESPLAYED");
			Attr gpvalue3 = gamedoc.createAttribute("value");
			gpvalue3.setValue(""+g.getTurn().getGamesPlayed());
			gplayed3.setAttributeNode(gpvalue3);
		
			Element gwon3 = gamedoc.createElement("GAMESWON");
			Attr gwvalue3 = gamedoc.createAttribute("value");
			gwvalue3.setValue(""+g.getTurn().getGamesWon());
			gwon3.setAttributeNode(gwvalue3);
		
			player3.appendChild(token3);
			player3.appendChild(total3);
			player3.appendChild(highest3);
			player3.appendChild(gplayed3);
			player3.appendChild(gwon3);
		
			turn.appendChild(player3);
		
			game.appendChild(turn);
		
			Element scoring = gamedoc.createElement("SCORING");
			Attr automatic = gamedoc.createAttribute("automatic");
			automatic.setValue((g.getScoringSystem().isAutomatic()) ? "y" : "n");
			scoring.setAttributeNode(automatic);
		
			game.appendChild(scoring);
		
			gamelist.appendChild(game);
		
			gamedoc.appendChild(gamedoc.adoptNode(gamelist));
		
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(gamedoc);
			StreamResult result = new StreamResult(new File("gamelist.xml"));
			transformer.transform(source, result);
		
		}
		catch (TransformerException tfe) 
		{
			tfe.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void removeGame(String id)
	{
		if(!parsed)
		{
			parse();
		}
		
		try
		{
			NodeList nList = gamedoc.getElementsByTagName("GAME");
			for(int i=0; i<nList.getLength(); i++)
			{
				Node rNode = nList.item(i);
				if(rNode.getAttributes().getNamedItem("id").getNodeValue().equals(id))
				{
					gamedoc.getElementsByTagName("GAMELIST").item(0).removeChild(rNode);	
				}
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(gamedoc);
			StreamResult result = new StreamResult(new File("gamelist.xml"));
			transformer.transform(source, result);
		}
		catch (TransformerException tfe) 
		{
		}
		catch(Exception e)
		{
		}
	}
	
	private static void parse()
	{
		try
		{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			gamedoc = dBuilder.parse(new File("gamelist.xml"));
			build();
			parsed = true;
		} 
		catch (Exception e) 
		{
		}
	}
	
	private static void build()
	{
		NodeList gameList = gamedoc.getElementsByTagName("GAME");
		for(int i=0; i<gameList.getLength(); i++)
		{
			Node gameNode = gameList.item(i);
			Board board = getBoard((Element) gameNode);
			Player p1 = getPlayer1((Element) gameNode);
			Player p2 = getPlayer2((Element) gameNode);
			ScoringSystem sc = getScoringSystem((Element) gameNode, board);
			Game game = new Game(p1, p2, board, sc);
			game.setTurn(getTurn((Element) gameNode));
			game.setDie(getDie((Element) gameNode));
			game.setId(getId((Element) gameNode));
			gamemap.put(getId(gameNode), game);
		}
	}
	
	private static String getId(Node game)
	{
		return game.getAttributes().getNamedItem("id").getNodeValue();
	}
	
	private static Die getDie(Element game)
	{
		Node dieNode = game.getElementsByTagName("DIE").item(0);
		Die die = new Die();
		die.setNumberRolled(Integer.parseInt(dieNode.getAttributes().getNamedItem("numberRolled").getNodeValue()));
		return die;
	}
	
	private static Board getBoard(Element game)
	{
		Element boardNode = (Element) game.getElementsByTagName("BOARD").item(0);
		NamedNodeMap attributes = boardNode.getAttributes();
		int rows = Integer.parseInt(attributes.getNamedItem("rows").getNodeValue());
		int columns = Integer.parseInt(attributes.getNamedItem("columns").getNodeValue());
		int[] upperPos = getUpperPositions(boardNode);
		int[] upperDes = getUpperDestinations(boardNode);
		int[] upperPoints = getUpperPoints(boardNode);
		int[] downerPos = getDownerPositions(boardNode);
		int[] downerDes = getDownerDestinations(boardNode);
		int[] downerPoints = getDownerPoints(boardNode);
		int[] bonusPos = getBonusPositions(boardNode);
		int[] bonusPoints = getBonusPoints(boardNode);
		return new Board(rows, columns, upperPos, upperDes, upperPoints, downerPos, downerDes, downerPoints, bonusPos, bonusPoints);
	}
	
	private static int[] getUpperPositions(Element boardNode)
	{
		NodeList nodes = boardNode.getElementsByTagName("UPPERS").item(0).getChildNodes();
		ArrayList<Integer> positions = new ArrayList<Integer>();
		for(int i=1; i<nodes.getLength(); i+=2)
		{
			positions.add(Integer.parseInt(nodes.item(i).getAttributes().getNamedItem("position").getNodeValue()));
		}
		int[] posarray = new int[positions.size()];
		for(int i=0; i<posarray.length; i++)
		{
			posarray[i] = positions.get(i);
		}
		return posarray;
	}
	
	private static int[] getUpperDestinations(Element boardNode)
	{
		NodeList nodes = boardNode.getElementsByTagName("UPPERS").item(0).getChildNodes();
		ArrayList<Integer> destinations = new ArrayList<Integer>();
		for(int i=1; i<nodes.getLength(); i+=2)
		{
			destinations.add(Integer.parseInt(nodes.item(i).getAttributes().getNamedItem("destination").getNodeValue()));
		}
		int[] desarray = new int[destinations.size()];
		for(int i=0; i<desarray.length; i++)
		{
			desarray[i] = destinations.get(i);
		}
		return desarray;
	}
	
	private static int[] getUpperPoints(Element boardNode)
	{
		NodeList nodes = boardNode.getElementsByTagName("UPPERS").item(0).getChildNodes();
		ArrayList<Integer> points = new ArrayList<Integer>();
		for(int i=1; i<nodes.getLength(); i+=2)
		{
			points.add(Integer.parseInt(nodes.item(i).getAttributes().getNamedItem("point").getNodeValue()));
		}
		int[] pointarray = new int[points.size()];
		for(int i=0; i<pointarray.length; i++)
		{
			pointarray[i] = points.get(i);
		}
		return pointarray;
	}
	
	private static int[] getDownerPositions(Element boardNode)
	{
		boardNode.getChildNodes().item(2).getChildNodes();
		NodeList nodes = boardNode.getElementsByTagName("DOWNERS").item(0).getChildNodes();
		ArrayList<Integer> positions = new ArrayList<Integer>();
		for(int i=1; i<nodes.getLength(); i+=2)
		{
			nodes.item(i).getAttributes().getNamedItem("position");
			positions.add(Integer.parseInt(nodes.item(i).getAttributes().getNamedItem("position").getNodeValue()));
		}
		int[] posarray = new int[positions.size()];
		for(int i=0; i<posarray.length; i++)
		{
			posarray[i] = positions.get(i);
		}
		return posarray;
	}
	
	private static int[] getDownerDestinations(Element boardNode)
	{
		NodeList nodes = boardNode.getElementsByTagName("DOWNERS").item(0).getChildNodes();
		ArrayList<Integer> destinations = new ArrayList<Integer>();
		for(int i=1; i<nodes.getLength(); i+=2)
		{
			destinations.add(Integer.parseInt(nodes.item(i).getAttributes().getNamedItem("destination").getNodeValue()));
		}
		int[] desarray = new int[destinations.size()];
		for(int i=0; i<desarray.length; i++)
		{
			desarray[i] = destinations.get(i);
		}
		return desarray;
	}
	
	private static int[] getDownerPoints(Element boardNode)
	{
		NodeList nodes = boardNode.getElementsByTagName("DOWNERS").item(0).getChildNodes();
		ArrayList<Integer> points = new ArrayList<Integer>();
		for(int i=1; i<nodes.getLength(); i+=2)
		{
			points.add(Integer.parseInt(nodes.item(i).getAttributes().getNamedItem("point").getNodeValue()));
		}
		int[] pointarray = new int[points.size()];
		for(int i=0; i<pointarray.length; i++)
		{
			pointarray[i] = points.get(i);
		}
		return pointarray;
	}
	
	private static int[] getBonusPositions(Element boardNode)
	{
		NodeList nodes = boardNode.getElementsByTagName("BONUSES").item(0).getChildNodes();
		ArrayList<Integer> positions = new ArrayList<Integer>();
		for(int i=0; i<nodes.getLength(); i++)
		{
			positions.add(Integer.parseInt(nodes.item(i).getAttributes().getNamedItem("position").getNodeValue()));
		}
		int[] posarray = new int[positions.size()];
		for(int i=0; i<posarray.length; i++)
		{
			posarray[i] = positions.get(i);
		}
		return posarray;
	}
	
	private static int[] getBonusPoints(Element boardNode)
	{
		NodeList nodes = boardNode.getElementsByTagName("BONUSES").item(0).getChildNodes();
		ArrayList<Integer> points = new ArrayList<Integer>();
		for(int i=0; i<nodes.getLength(); i++)
		{
			points.add(Integer.parseInt(nodes.item(i).getAttributes().getNamedItem("point").getNodeValue()));
		}
		int[] pointarray = new int[points.size()];
		for(int i=0; i<pointarray.length; i++)
		{
			pointarray[i] = points.get(i);
		}
		return pointarray;
	}
	
	private static Player getPlayer1(Element game)
	{
		//Element playerNode = game.getElementsByTagName("PLAYERS").((Element) item(0)).getElementByTagName("PLAYER").((Element) item(0));
		Element playersNode = (Element) game.getElementsByTagName("PLAYERS").item(0);
		Element playerNode = (Element) playersNode.getElementsByTagName("PLAYER").item(0);
		return makePlayer(playerNode);
	}
	
	private static Player getPlayer2(Element game)
	{
		Element playersNode = (Element) game.getElementsByTagName("PLAYERS").item(0);
		Element playerNode = (Element) playersNode.getElementsByTagName("PLAYER").item(1);
		return makePlayer(playerNode);
	}
	
	private static Player getTurn(Element game)
	{
		Element playersNode = (Element) game.getElementsByTagName("TURN").item(0);
		Element playerNode = (Element) playersNode.getElementsByTagName("PLAYER").item(0);
		return makePlayer(playerNode);
	}
	
	private static Player makePlayer(Element playerNode)
	{
		NamedNodeMap attributes = playerNode.getAttributes();
		String name = attributes.getNamedItem("name").getNodeValue();
		String type = attributes.getNamedItem("type").getNodeValue();
		Node tokenNode = playerNode.getElementsByTagName("TOKEN").item(0);
		attributes = tokenNode.getAttributes();
		String color = attributes.getNamedItem("color").getNodeValue();
		int position = Integer.parseInt(attributes.getNamedItem("position").getNodeValue());
		int totalScore = Integer.parseInt(playerNode.getElementsByTagName("TOTALSCORE").item(0).getAttributes().getNamedItem("value")
		.getNodeValue());
		int highestScore = Integer.parseInt(playerNode.getElementsByTagName("HIGHESTSCORE").item(0).getAttributes().getNamedItem("value")
		.getNodeValue());
		int gamesPlayed = Integer.parseInt(playerNode.getElementsByTagName("GAMESPLAYED").item(0).getAttributes().getNamedItem("value")
		.getNodeValue());
		int gamesWon = Integer.parseInt(playerNode.getElementsByTagName("GAMESWON").item(0).getAttributes().getNamedItem("value")
		.getNodeValue());
		Token token = new Token(color);
		token.setPosition(position);
		if(type.equals("h"))
		{
			return new HumanPlayer(name, token, totalScore, highestScore, gamesPlayed, gamesWon);
		}
		else
		{
			return new ComputerPlayer(name, token);
		}
	}
	
	private static ScoringSystem getScoringSystem(Element game, Board board)
	{
		Node scoring = game.getElementsByTagName("SCORING").item(0);
		return new ScoringSystem(board, isAutomatic(scoring));
	}
	
	private static boolean isAutomatic(Node scoring)
	{
		String automatic = scoring.getAttributes().getNamedItem("automatic").getNodeValue();
		if(automatic.equals("y"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}