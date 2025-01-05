import java.awt.Graphics;
import java.util.ArrayList;

public class BattleScreen {
    Panel panel;
    ArrayList<Questions> q = new ArrayList<>();
    int qCount = 0;
    int tries = 3;

    public BattleScreen(Panel panel) {
        this.panel = panel;
    }
    public void paint(Graphics g, int type) {
        // Set questions based on type
        switch (type) {
            case 1 -> polynomial1();
            case 2 -> polynomial2();
            case 3 -> trigonometry1();
            case 4 -> trigonometry2();
            case 5 -> exponential();
        }
        // Prevent out of bounds
        if (qCount < q.size()-1 && panel.gamePhase == 1) {
            q.get(qCount).paint(g);
        }
    }
    public void addComponents(){
        q.get(qCount).addComponents();
    }
    public void polynomial1() {
        String[] a = {"2", "3", "1", "4"};
        String[] b = {"x = -2, x = 3", "x = -1, x = 5","x = -3, x = 2", "x = 1, x = -5"};
        String[] c = {"6", "4", "2", "8"};
        String[] d = {"(x - 1)(x + 6)", "(x + 1)(x - 2)", "(x - 2)(x + 3)", "(x + 2)(x - 3)"};
        String[] e = {"2", "5", "4", "3"};
        
        q.clear();
        q.add(new Questions(panel, this, "Degree: y = x\u00B2 + 3x + 2", a, 0, 0));
        q.add(new Questions(panel, this, "Roots: f(x) = x\u00B2 - 4x - 5", b, 1, 0));
        q.add(new Questions(panel, this, "f(2): f(x) = x\u00B2 - 3x + 4", c, 2, 0));
        q.add(new Questions(panel, this, "Factor: f(x) = x\u00B2 + x - 6", d, 2, 0));
        q.add(new Questions(panel, this, "Y-int: f(x) = x\u00B2 - 2x + 3", e, 3, 0));
        q.add(new Questions(panel, this, "", a, 0, 0));
        addComponents();
    }
    public void polynomial2() {
        String[] a = {"1", "3", "2", "4"};
        String[] b = {"x = 0, x = 1, x = -4", "x = -1, x = 2, x = 4", "x = 0, x = -1, x = 4", "x = 1, x = 2, x = 3"};
        String[] c = {"2", "4", "6", "8"};
        String[] d = {"(x - 2)(x\u00B2 + 2x + 6)", "(x + 2)(x\u00B2 - x - 6)", "(x - 4)(x\u00B2 + x + 3)", "(x + 3)(x\u00B2 - 4)"};
        String[] e = {"2", "4", "5", "1"};
        
        q.clear();
        q.add(new Questions(panel, this, "Degree: y = 2x\u00B3 + 3x - 1", a, 1, 1));
        q.add(new Questions(panel, this, "Roots: f(x)= x\u00B3 − 3x\u00B2 − 4x", b, 0, 1));
        q.add(new Questions(panel, this, "f(2): f(x)= x\u00B3 − 2x\u00B2 + 3x −4", c, 0, 1));
        q.add(new Questions(panel, this, "Factor: f(x) = x\u00B3 + 3x\u00B2 - 4x - 12", d, 3, 1));
        q.add(new Questions(panel, this, "Y-int: f(x) = x\u00B3 - 4x\u00B2 + 2x + 5", e, 2, 1));
        q.add(new Questions(panel, this, "", a, 0, 0));
        addComponents();
    }
    public void trigonometry1() {
        String[] a = {"0", "π/2", "π", "√2/2"};
        String[] b = {"cos(3x)", "sinx", "cosx", "sin(3x)"};
        String[] c = {"-1", "√3/2", "√2/2", "1"};
        String[] d = {"1", "0.907", "-1", "0.965"};
        String[] e = {"√3/2", "1/2", "1", "0"};

        q.clear();
        q.add(new Questions(panel, this, "sin(π/4)", a, 3, 2));
        q.add(new Questions(panel, this, "Simplify: sin(2x) co(2x) - cos(2x) sin(2x)", b, 1, 2));
        q.add(new Questions(panel, this, "Simplify & Evaluate: 2sin(π/8)cos(π/8)", c, 2, 2));
        q.add(new Questions(panel, this, "IROC at 10: f(x) = 20sin(πx/60)+25", d, 1, 2)); 
        q.add(new Questions(panel, this, "sin(π/3)", e, 0, 2));
        q.add(new Questions(panel, this, "", a, 0, 0));
        addComponents();
    }
    public void trigonometry2() {
        String[] a = {"1/2", "√3/2", "1", "0"};
        String[] b = {"(√2+√6)/4", "(√2-√6)/4", "√4", "2+√6"};
        String[] c = {"-√3/2", "(√2+√6)/4", "-(√2+6)/4", "√2/2"};
        String[] d = {"-1/2", "0.5", "0.707", "0.3"};
        String[] e = {"1/2", "√3/2", "0", "1"};

        q.clear();
        q.add(new Questions(panel, this, "cos(π/6)", a, 1, 3));
        q.add(new Questions(panel, this, "Exact Value: cos(23π/12)", b, 0, 3));
        q.add(new Questions(panel, this, "Exact Value: cos(11π/12)", c, 3, 3));
        q.add(new Questions(panel, this, "IROC at 10: f(x) = 15cos(πx/30) + 20", d, 1, 3)); 
        q.add(new Questions(panel, this, "cos(π/2)", e, 2, 3));
        q.add(new Questions(panel, this, "", a, 0, 0));
        addComponents();
    }
    public void exponential() {
        String[] a = {"8", "3", "16", "4"};
        String[] b = {"3", "8", "6", "4"};
        String[] c = {"2", "1", "4", "3"};
        String[] d = {"4", "1", "3", "5"};
        String[] e = {"x = 2", "x = 1", "x = 4", "x = 9"};
        
        q.clear();
        q.add(new Questions(panel, this, "Evaluate 2\u00B3", a, 0, 4));
        q.add(new Questions(panel, this, "Evaluate log\u2082(64)", b, 2, 4));
        q.add(new Questions(panel, this, "Evaluate log\u2085(25)", c, 0, 4));
        q.add(new Questions(panel, this, "Evaluate log\u2082(32)", d, 3, 4)); 
        q.add(new Questions(panel, this, "Solve 3^2x = 81", e, 0, 4));
        q.add(new Questions(panel, this, "", a, 0, 0));
        addComponents();
    }
    public void reset() {
        panel.removeAll();
        qCount = 0;
        tries = 3;
        panel.keyH.changePhasePressed = false;
    }
}
