
public class InferenciaDifusa extends javax.swing.JFrame implements javax.swing.event.ChangeListener{
    
    private net.sourceforge.jFuzzyLogic.FIS FIS;
    private javax.swing.JSlider sldTemp;
    
    private net.sourceforge.jFuzzyLogic.plot.JDialogFis dialogFIS;
    
    public InferenciaDifusa(){
        super("Inferencia Difusa");
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setSize(400,400);
        
        FIS = net.sourceforge.jFuzzyLogic.FIS.load("Logica.fcl",true);
        
        FIS.setVariable("temperatura",17.5);
        FIS.evaluate();
        double res = FIS.getVariable("velocidad").getLatestDefuzzifiedValue();
        System.out.println("Valor: "+res);
        
        getContentPane().setLayout(new java.awt.FlowLayout());
        initComponentes();
    }
    
    private void initComponentes(){
        sldTemp = new javax.swing.JSlider();
        sldTemp.setMaximum(400);
        sldTemp.setMinimum(0);
        sldTemp.setPaintLabels(true);
        sldTemp.setPaintTicks(true);
        sldTemp.setMajorTickSpacing(70);
        sldTemp.setMinorTickSpacing(30);
        
        sldTemp.addChangeListener(this);
        
        getContentPane().add(sldTemp);
        
        dialogFIS = new net.sourceforge.jFuzzyLogic.plot.JDialogFis(FIS);
        net.sourceforge.jFuzzyLogic.plot.JFuzzyChart.get().chart(FIS.getVariable("velocidad"), FIS.getVariable("velocidad").getDefuzzifier(), true);
    }
    
    public void stateChanged(javax.swing.event.ChangeEvent ce){
        double v =((double)sldTemp.getValue())/10;
        System.out.println("valor: "+v);
        FIS.setVariable("temperatura",v);
        FIS.evaluate();
        dialogFIS.repaint();
        net.sourceforge.jFuzzyLogic.plot.JFuzzyChart.get().chart(FIS.getVariable("velocidad"), FIS.getVariable("velocidad").getDefuzzifier(), true);        
        }    
    
    public static void main(String [] args){
        new InferenciaDifusa().setVisible(true);
    }
}
