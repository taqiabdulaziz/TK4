import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.InputMismatchException;

public class App extends JFrame {
    private JPanel myPanel;
    private JButton clearTableBtn;
    private JButton simpanBtn;
    private JTextField noBukuTxt;
    private JButton hapusButton;
    private JButton editBtn;
    private JTextField judulTxt;
    private JTextField thnTxt;
    private JTextField penerbitTxt;
    private JTable table1;
    private JScrollPane sp;
    private JButton keluarBtn;
    private JButton bacaTableBtn;
    private JTextField pengarangTxt;
    private JLabel noBukuLbl;
    private DefaultTableModel model;

    public void clearAllTextField() {
        noBukuTxt.setText("");
        judulTxt.setText("");
        pengarangTxt.setText("");
        thnTxt.setText("");
        penerbitTxt.setText("");
    }

    public App() {
        this.setTitle("TK4 - JABA -TEAM 1");
        this.pack();
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setContentPane(myPanel);

        keluarBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
                super.mouseClicked(e);
            }
        });
        simpanBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    String noBuku = noBukuTxt.getText();
                    String judulBuku = judulTxt.getText();
                    String pengarang = pengarangTxt.getText();
                    String tahun = thnTxt.getText();
                    String penerbit = penerbitTxt.getText();
                    validateInput(
                            noBuku,
                            judulBuku,
                            pengarang,
                            tahun,
                            penerbit
                    );

                    Object[] dt = {
                            noBukuTxt.getText(),
                            judulTxt.getText(),
                            pengarangTxt.getText(),
                            thnTxt.getText(),
                            penerbitTxt.getText(),
                    };
                    model.addRow(dt);
                } catch (InputMismatchException ignored) {
                    clearAllTextField();
                }
                super.mouseClicked(e);
            }
        });
        clearTableBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rc = model.getRowCount();
                for (int i = rc - 1; i >= 0; i--) {
                    model.removeRow(i);
                }
                clearAllTextField();
                super.mouseClicked(e);
            }
        });

        /*
         *  Asumsi Edit by No. Buku
         * */
        editBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String noBuku = noBukuTxt.getText();
                String judulBuku = judulTxt.getText();
                String pengarang = pengarangTxt.getText();
                String tahun = thnTxt.getText();
                String penerbit = penerbitTxt.getText();
                int rc = model.getRowCount();
                for (int i = rc - 1; i >= 0; i--) {
                    if (
                            noBuku.equals(model.getValueAt(i, 0).toString())
                    ) {
                        Object[] dt = {
                                noBuku,
                                judulBuku,
                                pengarang,
                                tahun,
                                penerbit
                        };
                        model.addRow(dt);
                        model.removeRow(i);
                        break;
                    }
                }
                clearAllTextField();
                super.mouseClicked(e);
            }
        });
        hapusButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String noBuku = noBukuTxt.getText();
                String judulBuku = judulTxt.getText();
                String pengarang = pengarangTxt.getText();
                String tahun = thnTxt.getText();
                String penerbit = penerbitTxt.getText();
                int rc = model.getRowCount();
                for (int i = rc - 1; i >= 0; i--) {
                    if (
                            noBuku.equals(model.getValueAt(i, 0).toString()) &&
                                    judulBuku.equals(model.getValueAt(i, 1).toString()) &&
                                    pengarang.equals(model.getValueAt(i, 2).toString()) &&
                                    tahun.equals(model.getValueAt(i, 3).toString()) &&
                                    penerbit.equals(model.getValueAt(i, 4).toString())
                    ) {
                        model.removeRow(i);
                        break;
                    }
                }
                clearAllTextField();
                super.mouseClicked(e);
            }
        });
    }

    public static void validateInput(String noBuku, String judulBuku, String pengarang, String tahun, String penerbit) throws InputMismatchException {
        if (noBuku.isBlank()) {
            JOptionPane.showMessageDialog(null, "no buku tidak bisa kosong");
            throw new InputMismatchException();
        }

        if (judulBuku.isBlank()) {
            JOptionPane.showMessageDialog(null, "judul buku tidak bisa kosong");
            throw new InputMismatchException();
        }

        if (pengarang.isBlank()) {
            JOptionPane.showMessageDialog(null, "pengarang tidak bisa kosong");
            throw new InputMismatchException();
        }

        if (tahun.isBlank()) {
            JOptionPane.showMessageDialog(null, "tahun tidak bisa kosong");
            throw new InputMismatchException();
        }

        if (penerbit.isBlank()) {
            JOptionPane.showMessageDialog(null, "penerbit tidak bisa kosong");
            throw new InputMismatchException();
        }
    }

    public static void create() {
        new App();
    }

    public static void main(String[] args) {
        create();
    }

    private void createUIComponents() {
        table1 = new JTable();
        String[] columns = {
                "No Buku",
                "Judul Buku",
                "Pengarang",
                "Tahun",
                "Penerbit"
        };
        model = new DefaultTableModel(columns, 0);

        table1.setModel(model);
    }
}