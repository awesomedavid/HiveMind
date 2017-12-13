import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Cell {
	private float x;
	private float y;
	private double life;
	private int cid;
	private int fid;
	private int numAlive;
	public Cell(int xPos, int yPos) {
		x = xPos;
		y = yPos;
		life = Math.random();
	fid = 1;
		if(life >.1)
		{
		cid = 1;
		}else {
		cid = 0;
		}
		numAlive = 0;
	}

	public void draw(Graphics g)
	{		
		if(cid == 1) {
			g.setColor(new Color(0,175,255));
			g.fillRect(x*v.cell_size, y*v.cell_size, v.cell_size-1,v.cell_size-1);
		}else {
//			g.setColor(new Color(25,255,255));
//			g.fillRect(x*v.cell_size, y*v.cell_size, v.cell_size-1,v.cell_size-1);
		}
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}

	public int getNumAlive() {
		return numAlive;
	}

	public void setNumAlive(int numAlive) {
		this.numAlive = numAlive;
	}
	
}

