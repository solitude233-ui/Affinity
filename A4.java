package ca.queensu.cs.cisc124.asgmt.a4;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


import ca.queensu.cs.cisc124.notes.basics.geometry.Point2;
import princeton.introcs.StdDraw;

public class A4 {
	
	Polygon sq1;
	Polygon sq2;
	Polygon sq3;
	Polygon sq4;
	
	A4() {
		// square centered at the origin
		sq1 = new Polygon(Color.BLUE, 
					new Point2(-1, -1), 
					new Point2(-1, 1), 
					new Point2(1, 1),
					new Point2(1, -1));

		// square centered at (-9, -4.5)
		sq2 = new Polygon(Color.RED, 
					new Point2(-10, -5.5), 
					new Point2(-10, -3.5), 
					new Point2(-8, -3.5),
					new Point2(-8, -5.5));

		// square centered at (5, 0)
		sq3 = new Polygon(Color.GREEN, 
					new Point2(4, -1), 
					new Point2(4, 1), 
					new Point2(6, 1),
					new Point2(6, -1));
			
		// square centered at (7, 0)
		sq4 = new Polygon(Color.MAGENTA, 
					new Point2(6.5, -0.5), 
					new Point2(6.5, 0.5), 
					new Point2(7.5, 0.5),
					new Point2(7.5, -0.5));
	}

	static class Polygon {
		List<Point2> vertices;
		Point2 center;
		Color color;

		Polygon(Color c, Point2... vertices) {
			this.color = c;
			this.vertices = new ArrayList<>();
			this.center = new Point2();
			for (Point2 v : vertices) {
				this.vertices.add(v);
				this.center.set(this.center.x() + v.x(), this.center.y() + v.y());
			}
			this.center.divide(vertices.length);
		}

		void transform(AbstractAffinity t) {
			for (Point2 v : vertices) {
				t.transform(v);
			}
			t.transform(this.center);
		}
	}

	public void draw(Polygon... polygons) {
		for (Polygon p : polygons) {
			double[] x = new double[p.vertices.size()];
			double[] y = new double[p.vertices.size()];
			for (int i = 0; i < x.length; i++) {
				x[i] = p.vertices.get(i).x();
				y[i] = p.vertices.get(i).y();
			}
			StdDraw.setPenColor(p.color);
			StdDraw.filledPolygon(x, y);
		}
	}
	
	public void animate() {
		double theta = Math.toRadians(1.0);
		
		AbstractAffinity t1 = new Rotation(theta);
		this.sq1.transform(t1);
		
		if (this.sq2.center.x() > 8.9) {
			AbstractAffinity t2 = new Translation(-18.0, -9.0);
			this.sq2.transform(t2);
		}
		AbstractAffinity t2 = new Translation(0.1, 0.05);
		this.sq2.transform(t2);
		
		Affinity t3 = new Affinity();
		AbstractAffinity remRot3 = new RemoteRotation(theta, this.sq3.center);
		t3.premultiply(remRot3);
		t3.premultiply(t1);
		this.sq3.transform(t3);
		
		Affinity t4 = new Affinity();
		AbstractAffinity remRot41 = new RemoteRotation(5 * theta, this.sq4.center);
		AbstractAffinity remRot42 = new RemoteRotation(3 * theta, this.sq3.center);
		t4.premultiply(remRot41);
		t4.premultiply(remRot42);
		t4.premultiply(t1);
		this.sq4.transform(t4);
		
		StdDraw.clear();
		this.draw(this.sq1, this.sq2, this.sq3, this.sq4);
		
	}

	public static void main(String[] args) {
		StdDraw.setScale(-10, 10);
		A4 ani = new A4();
		while (true) {
			ani.animate();
			try {
				Thread.sleep(30);
			}
			catch (InterruptedException x) {
				
			}
		}
	}

}
