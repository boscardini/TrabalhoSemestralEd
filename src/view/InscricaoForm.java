package view;

import controller.InscricaoController;
import model.dto.Inscricao;
import br.edu.fateczl.Lista;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class InscricaoForm extends JDialog {

    private JTextField txtId, txtIdProcesso, txtIdProfessor;
    private JButton btnSalvar, btnAtualizar, btnRemover, btnConsultar, btnLimpar;
    private InscricaoController controller;

    public InscricaoForm(Window owner) throws Exception {
        super(owner, "Inscrições");
        controller = new InscricaoController();
        initComponents();
        pack();
        setLocationRelativeTo(owner);
    }

    private void initComponents() {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

       

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("ID da Disciplina:"), gbc);

        gbc.gridx = 1;
        txtId = new JTextField(15);
        add(txtId, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("ID do Processo:"), gbc);

        gbc.gridx = 1;
        txtIdProcesso = new JTextField(15);
        add(txtIdProcesso, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("ID do Professor:"), gbc);

        gbc.gridx = 1;
        txtIdProfessor = new JTextField(15);
        add(txtIdProfessor, gbc);

        

        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> { onSalvar(); limparCampos(); });
        panelBotoes.add(btnSalvar);

        btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(e -> { onAtualizar(); limparCampos(); });
        panelBotoes.add(btnAtualizar);

        btnRemover = new JButton("Remover");
        btnRemover.addActionListener(e -> { onRemover(); limparCampos(); });
        panelBotoes.add(btnRemover);

        btnConsultar = new JButton("Consultar");
        btnConsultar.addActionListener(e -> onConsultar());
        panelBotoes.add(btnConsultar);

        btnLimpar = new JButton("Limpar");
        btnLimpar.addActionListener(e -> limparCampos());
        panelBotoes.add(btnLimpar);

        gbc.gridx = 0; 
        gbc.gridy = 3; 
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(panelBotoes, gbc);
    }

   

    private void onSalvar() {
        try {
            Inscricao ins = new Inscricao(
                    Integer.parseInt(txtId.getText()),
                    Integer.parseInt(txtIdProcesso.getText()),
                    Integer.parseInt(txtIdProfessor.getText()),
                    LocalDate.now()
            );

            controller.inserir(ins);
            JOptionPane.showMessageDialog(this, "Inscrição salva");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void onAtualizar() {
        try {
            Inscricao ins = new Inscricao(
                    Integer.parseInt(txtId.getText()),
                    Integer.parseInt(txtIdProcesso.getText()),
                    Integer.parseInt(txtIdProfessor.getText()),
                    LocalDate.now()
            );

            controller.atualizar(ins);
            JOptionPane.showMessageDialog(this, "Inscrição atualizada");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void onRemover() {
        try {
            int id = Integer.parseInt(txtId.getText());

            int confirma = JOptionPane.showConfirmDialog(
                    this,
                    "Deseja realmente excluir esta inscrição?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirma == JOptionPane.YES_OPTION) {
                controller.excluir(id);
                JOptionPane.showMessageDialog(this, "Inscrição removida");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void onConsultar() {
        try {
            Lista<Inscricao> lista = controller.listarTodos();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < lista.size(); i++) {
                Inscricao ins = lista.get(i);

                sb.append(String.format(
                        "ID: %d - Processo: %d - Professor: %s - Data: %s\n",
                        ins.getId(),
                        ins.getIdProcesso(),
                        ins.getIdProfessor(),
                        ins.getDataFormatada()
                ));
            }

            JTextArea ta = new JTextArea(sb.toString());
            ta.setEditable(false);

            JScrollPane sp = new JScrollPane(ta);
            sp.setPreferredSize(new Dimension(500, 300));

            JOptionPane.showMessageDialog(
                    this, sp, "Lista de Inscrições", JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

     

    private void limparCampos() {
        txtId.setText("");
        txtIdProcesso.setText("");
        txtIdProfessor.setText("");
        txtId.requestFocus();
    }
}

