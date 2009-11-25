package guri.screenmodel.test;

import guri.screenmodel.ModUnit;
import guri.screenmodel.ShapeUnit;
import guri.screenmodel.TextUnit;
import guri.screenmodel.UnitActionDetector;
import guri.screenmodel.UnitCanvas;
import guri.screenmodel.UnitLayering;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;

public class Test1 extends JFrame implements UnitActionDetector.Listener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9131358279996998156L;

	public Test1() {
		super("Test 1");
		initCanvas();
		initShapeUnit();
		initModShapeUnit();
		initModTextUnit();
	}

	public static void main(String[] argv) {
		Test1 t1 = new Test1();
		t1.setSize(200, 200);
		t1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		t1.setVisible(true);
	}

	// text

	UnitActionDetector textUnitDetectorM;

	ModUnit modTextUnitM;

	TextUnit modTextUnitT;

	private void initModTextUnit() {
		modTextUnitT = new TextUnit(new Font(null, Font.PLAIN, 12), "Hello", 0);
		modTextUnitM = new ModUnit(modTextUnitT);
		modTextUnitM.affineTransform.translate(100, 100);
		modTextUnitM.color = new Color(0x000000);
		textUnitDetectorM = new UnitActionDetector();
		textUnitDetectorM.setUnit(modTextUnitM);
		textUnitDetectorM.addListener(this);
		unitLayering.setUnit(2, textUnitDetectorM);
	}

	// mod-shape

	UnitActionDetector modShapeUnitDetectorM;

	UnitActionDetector modShapeUnitDetectorS;

	ModUnit modShapeUnitM;

	ShapeUnit modShapeUnitS;

	private void initModShapeUnit() {
		modShapeUnitS = new ShapeUnit(new Ellipse2D.Float(0, 0, 50, 50));
		modShapeUnitDetectorS = new UnitActionDetector();
		modShapeUnitDetectorS.setUnit(modShapeUnitS);
		modShapeUnitDetectorS.addListener(this);
		modShapeUnitM = new ModUnit(new ModUnit(modShapeUnitDetectorS));
		modShapeUnitM.affineTransform.setToTranslation(100, 150);
		modShapeUnitDetectorM = new UnitActionDetector();
		modShapeUnitDetectorM.setUnit(modShapeUnitM);
		modShapeUnitDetectorM.addListener(this);
		unitLayering.setUnit(1, modShapeUnitDetectorM);
	}

	// shape

	UnitActionDetector shapeUnitDetector;

	ShapeUnit shapeUnit;

	private void initShapeUnit() {
		shapeUnit = new ShapeUnit(new Ellipse2D.Float(0, 0, 100, 100));
		shapeUnitDetector = new UnitActionDetector();
		shapeUnitDetector.setUnit(shapeUnit);
		shapeUnitDetector.addListener(this);
		unitLayering.setUnit(0, shapeUnitDetector);
	}

	// canvas

	UnitLayering unitLayering;

	UnitCanvas canvas;

	private void initCanvas() {
		unitLayering = new UnitLayering(10);
		canvas = new UnitCanvas(unitLayering);
		this.add(canvas);
	}

	// mouse event detection

	public void mouseEvent(UnitActionDetector uad, MouseEvent me, Object obj) {
		if (uad == shapeUnitDetector) {
			System.out.println("shapeUnitDetector: " + me.toString());
		} else if (uad == modShapeUnitDetectorM) {
			System.out.println("modShapeUnitDetectorM: " + me.toString());
		} else if (uad == modShapeUnitDetectorS) {
			System.out.println("modShapeUnitDetectorS: " + me.toString());
		} else if (uad == textUnitDetectorM) {
			System.out.println("textUnitDetectorM: " + me.toString());
		} else {
			assert (false);
		}
	}

}
