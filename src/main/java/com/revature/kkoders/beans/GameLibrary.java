package com.revature.kkoders.beans;

import java.util.List;

public interface GameLibrary
{
	public long getId();
	public void setId(long id);
	public List<GameImpl> getGames();
	public void setGames(List<GameImpl> games);
}
