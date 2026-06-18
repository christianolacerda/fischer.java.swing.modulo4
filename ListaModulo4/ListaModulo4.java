import java.awt.*;
import javax.swing.*;

public class ListaModulo4 extends JFrame {

    public ListaModulo4() {
        super("Questão 1 - Gerenciadores de Layout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        JTabbedPane abas = new JTabbedPane();
        abas.addTab("a) FlowLayout",   criarPainelA());
        abas.addTab("b) BorderLayout", criarPainelB());
        abas.addTab("c) GridLayout",   criarPainelC());
        abas.addTab("d) BoxLayout",    criarPainelD());

        add(abas);
        setVisible(true);
    }

    // a) Botões da esquerda para direita (FlowLayout)
    private JPanel criarPainelA() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painel.add(new JButton("Um"));
        painel.add(new JButton("Dois"));
        painel.add(new JButton("Três"));
        painel.add(new JButton("Quatro"));
        return painel;
    }

    // b) Botões nas áreas Norte, Sul, Leste, Oeste e Centro (BorderLayout)
    private JPanel criarPainelB() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.add(new JButton("Botão 1"), BorderLayout.NORTH);
        painel.add(new JButton("Botão 2"), BorderLayout.CENTER);
        painel.add(new JButton("Botão 3"), BorderLayout.WEST);
        painel.add(new JButton("Botão 4"), BorderLayout.SOUTH);
        painel.add(new JButton("Botão 5"), BorderLayout.EAST);
        return painel;
    }

    // c) Botões simulando uma tabela (GridLayout 3x2)
    private JPanel criarPainelC() {
        JPanel painel = new JPanel(new GridLayout(3, 2));
        painel.add(new JButton("Botão 1"));
        painel.add(new JButton("Botão 2"));
        painel.add(new JButton("Botão 3"));
        painel.add(new JButton("Botão 4"));
        painel.add(new JButton("Botão 5"));
        painel.add(new JLabel(""));
        return painel;
    }

    // d) Botões na vertical ao centro (BoxLayout Y_AXIS)
    private JPanel criarPainelD() {
        JPanel painelExterno = new JPanel(new GridBagLayout());

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));

        for (int i = 1; i <= 5; i++) {
            JButton btn = new JButton("Botão " + i);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            painelBotoes.add(btn);
        }

        painelExterno.add(painelBotoes);
        return painelExterno;
    }

    public static void main(String[] args) {
        new ListaModulo4();
    }
}