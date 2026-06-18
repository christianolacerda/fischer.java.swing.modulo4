import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class ListaModulo4Q2 extends JFrame {

    public ListaModulo4Q2() {
        super("Questão 2 - Wireframes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(780, 520);
        setLocationRelativeTo(null);

        JTabbedPane abas = new JTabbedPane();
        abas.addTab("a) Formulário + Conteúdo", criarPainelA());
        abas.addTab("b) Portal/Blog",            criarPainelB());
        abas.addTab("c) Editor de Post",         criarPainelC());

        add(abas);
        setVisible(true);
    }

    // a) Formulário (Campo 1-4 + Salvar) | Título + Imagens + Texto + Rodapé
    private JPanel criarPainelA() {
        JPanel painel = new JPanel(new GridLayout(1, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Lado esquerdo: formulário
        JPanel painelEsq = new JPanel(new GridBagLayout());
        painelEsq.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;

        String[] campos = {"Campo 1:", "Campo 2:", "Campo 3:", "Campo 4:"};
        for (int i = 0; i < campos.length; i++) {
            c.gridx = 0; c.gridy = i; c.weightx = 0;
            painelEsq.add(new JLabel(campos[i]), c);
            c.gridx = 1; c.weightx = 1.0;
            painelEsq.add(new JTextField(15), c);
        }

        JButton btnSalvar = new JButton("Salvar");
        c.gridx = 0; c.gridy = campos.length;
        c.gridwidth = 2; c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        c.ipady = 15;
        painelEsq.add(btnSalvar, c);

        // Lado direito: título + imagem/texto + rodapé
        JPanel painelDir = new JPanel(new BorderLayout(5, 5));
        painelDir.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel lblTitulo = new JLabel("Título", SwingConstants.CENTER);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 13f));
        lblTitulo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        painelDir.add(lblTitulo, BorderLayout.NORTH);

        JPanel painelConteudo = new JPanel(new GridLayout(1, 3, 5, 5));
        painelConteudo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        painelConteudo.add(criarPlaceholderImagem("Colocar\numa\nimagem\naqui"));

        JTextArea txtArea = new JTextArea(
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
            "Etiam eget ligula eu lectus lobortis condimentum. Aliquam nonummy");
        txtArea.setLineWrap(true);
        txtArea.setWrapStyleWord(true);
        txtArea.setEditable(false);
        txtArea.setFont(txtArea.getFont().deriveFont(Font.ITALIC));
        painelConteudo.add(new JScrollPane(txtArea));
        painelConteudo.add(criarPlaceholderImagem("Colocar\numa\nimagem\naqui"));
        painelDir.add(painelConteudo, BorderLayout.CENTER);

        JLabel rodape = new JLabel("Rodapé", SwingConstants.CENTER);
        rodape.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));
        painelDir.add(rodape, BorderLayout.SOUTH);

        painel.add(painelEsq);
        painel.add(painelDir);
        return painel;
    }

    // b) Portal/Blog: Título | Menu lateral | Grid de posts | Sidebar | Rodapé
    private JPanel criarPainelB() {
        JPanel painel = new JPanel(new BorderLayout(5, 5));

        JLabel titulo = new JLabel("Título", SwingConstants.CENTER);
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 15f));
        titulo.setOpaque(true);
        titulo.setBackground(new Color(230, 230, 230));
        titulo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        titulo.setPreferredSize(new Dimension(0, 38));
        painel.add(titulo, BorderLayout.NORTH);

        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setBackground(new Color(245, 245, 245));
        menu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.GRAY));
        menu.setPreferredSize(new Dimension(95, 0));
        for (String item : new String[]{"Cliente", "Produto", "Vendas", "Arquivos"}) {
            JLabel lbl = new JLabel(item);
            lbl.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
            lbl.setForeground(new Color(180, 60, 60));
            menu.add(lbl);
        }
        painel.add(menu, BorderLayout.WEST);

        JPanel areaPosts = new JPanel(new GridLayout(2, 2, 5, 5));
        areaPosts.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        for (int i = 0; i < 4; i++) {
            areaPosts.add(criarCardPost());
        }
        painel.add(new JScrollPane(areaPosts), BorderLayout.CENTER);

        JPanel sidebar = new JPanel(new BorderLayout());
        sidebar.setBackground(new Color(235, 235, 235));
        sidebar.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.GRAY));
        sidebar.setPreferredSize(new Dimension(105, 0));
        sidebar.add(new JLabel(
                "<html><center>Área para<br>propagandas</center></html>",
                SwingConstants.CENTER), BorderLayout.CENTER);
        painel.add(sidebar, BorderLayout.EAST);

        JLabel rodape = new JLabel("Rodapé", SwingConstants.CENTER);
        rodape.setOpaque(true);
        rodape.setBackground(new Color(230, 230, 230));
        rodape.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));
        rodape.setPreferredSize(new Dimension(0, 32));
        painel.add(rodape, BorderLayout.SOUTH);

        return painel;
    }

    // c) Editor: Título | (TextArea + botões) | (Imagem + Carregar)
    private JPanel criarPainelC() {
        JPanel painel = new JPanel(new BorderLayout(5, 5));

        JLabel titulo = new JLabel("Título", SwingConstants.CENTER);
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 13f));
        titulo.setOpaque(true);
        titulo.setBackground(new Color(230, 230, 230));
        titulo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        titulo.setPreferredSize(new Dimension(0, 36));
        painel.add(titulo, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel(new GridLayout(1, 2, 10, 0));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        // Coluna esquerda
        JPanel colEsq = new JPanel(new BorderLayout(5, 5));
        colEsq.add(new JLabel("Título do texto"), BorderLayout.NORTH);

        JTextArea textArea = new JTextArea(
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
            "Etiam eget ligula eu lectus lobortis condimentum. " +
            "Aliquam nonummy auctor massa. Pellentesque habitant morbi " +
            "tristique senectus et netus et malesuada fames ac turpis egestas. " +
            "Nulla at risus. Quisque purus magna, auctor et, sagittis ac,");
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(textArea.getFont().deriveFont(Font.ITALIC));
        textArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        colEsq.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        painelBotoes.add(new JButton("Salvar"));
        painelBotoes.add(new JButton("Cancelar"));
        painelBotoes.add(new JButton("Voltar"));
        colEsq.add(painelBotoes, BorderLayout.SOUTH);

        // Coluna direita
        JPanel colDir = new JPanel(new BorderLayout(5, 5));
        JLabel imgPlaceholder = new JLabel("Colocar uma imagem aqui", SwingConstants.CENTER);
        imgPlaceholder.setOpaque(true);
        imgPlaceholder.setBackground(new Color(220, 220, 220));
        imgPlaceholder.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        colDir.add(imgPlaceholder, BorderLayout.CENTER);
        colDir.add(new JButton("Carregar imagem"), BorderLayout.SOUTH);

        painelCentral.add(colEsq);
        painelCentral.add(colDir);
        painel.add(painelCentral, BorderLayout.CENTER);

        return painel;
    }

    // Utilitários
    private JLabel criarPlaceholderImagem(String texto) {
        JLabel label = new JLabel(
                "<html><center>" + texto.replace("\n", "<br>") + "</center></html>",
                SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(new Color(220, 220, 220));
        label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        label.setPreferredSize(new Dimension(90, 120));
        return label;
    }

    private JPanel criarCardPost() {
        JPanel card = new JPanel(new BorderLayout(3, 3));
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JLabel lblTitulo = new JLabel("Título", SwingConstants.CENTER);
        lblTitulo.setForeground(new Color(180, 60, 60));
        lblTitulo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        card.add(lblTitulo, BorderLayout.NORTH);

        JLabel lblPostagem = new JLabel("Postagem", SwingConstants.CENTER);
        lblPostagem.setForeground(new Color(180, 60, 60));
        card.add(lblPostagem, BorderLayout.CENTER);

        JLabel lblAutor = new JLabel("Autor", SwingConstants.CENTER);
        lblAutor.setForeground(new Color(180, 60, 60));
        lblAutor.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));
        card.add(lblAutor, BorderLayout.SOUTH);

        return card;
    }

    public static void main(String[] args) {
        new ListaModulo4Q2();
    }
}