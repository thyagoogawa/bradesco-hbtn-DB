package demo;

import entities.Aluno;
import entities.Curso;
import entities.Endereco;
import entities.MaterialCurso;
import entities.Professor;
import entities.Telefone;
import models.AlunoModel;
import models.CursoModel;

import java.util.Calendar;
import java.util.List;

public class GestaoCursosMain {

    static AlunoModel alunoModel = new AlunoModel();
    static CursoModel cursoModel = new CursoModel();

    public static void main(String[] args) {
//        salvarAlunos();
//        buscarAlunoPorId(4L);
//        buscarTodosOsAlunos();
//        atualizarAluno();
//        deletarAluno(5L);

        salvarAluno();
        salvarCurso();
        associarAlunoAoCurso();

    }

    private static void salvarAluno() {
        Aluno aluno = new Aluno(null, "Vladmir da Silva", "8698",
                getCalendarDate(), "vladmir.silva@email.com");
        Telefone fone1 = new Telefone(null, "11", "98765-4321");
        fone1.setAluno(aluno);
        aluno.setTelefones(List.of(fone1));

        Endereco endereco1 = new Endereco(null, "Rua das Flores", "345", "Apto 45",
                "Jardim das Acacias", "São Paulo", "SP", "12345-678");
        endereco1.setAluno(aluno);
        aluno.setEnderecos(List.of(endereco1));

        alunoModel.create(aluno);
    }

    private static void salvarCurso() {
        Curso curso = new Curso(null, "Java Spring Boot", "JSB");
        MaterialCurso materialCurso = new MaterialCurso(null, "http://www.cursojava.com.br");
        materialCurso.setCurso(curso);
        curso.setMaterialCurso(materialCurso);

        Professor professor = new Professor(null, "Carlos da Silva", "246",
                "carlos.silva@email.com.br");
        professor.setCursos(List.of(curso));
        curso.setProfessor(professor);

        cursoModel.create(curso);
    }

    private static void associarAlunoAoCurso() {
        Aluno aluno = alunoModel.findById(2L);
        Curso curso = cursoModel.findById(2L);

        aluno.setCursos(List.of(curso));
        curso.setAlunos(List.of(aluno));

        // Como a relacao nao tem owner, tanto alunoModel.update, quanto cursoModel.update funcionam
        alunoModel.update(aluno);
//        cursoModel.update(curso);

    }

    private static void salvarAlunos() {
        System.out.println("Iniciando a aplicação de Gestão de Cursos");

        //Criando um aluno na base de dados
        Aluno aluno = new Aluno(null, "Vladmir da Silva", "8698",
                getCalendarDate(), "vladmir.silva@email.com");

        // Criando um aluno com telefone
        Aluno aluno2 = new Aluno(null, "Cleber dos Santos", "5679",
                getCalendarDate(), "cleber.santos@email.com");
        Telefone fone1 = new Telefone(null, "11", "98765-4321");

        Telefone fone2 = new Telefone(null, "11", "3748-9645");
        fone1.setAluno(aluno2);
        fone2.setAluno(aluno2);

        aluno2.setTelefones(List.of(fone1, fone2));

        // Criando um aluno com Endereco
        Aluno aluno3 = new Aluno(null, "Ana Silvia Bortelloni", "4645",
                getCalendarDate(), "ana.bortelloni@email.com");
        Endereco endereco1 = new Endereco(null, "Rua das Flores", "345", "Apto 45",
                "Jardim das Acacias", "São Paulo", "SP", "12345-678");
        Endereco endereco2 = new Endereco(null, "Rua das Pedras", "576", "Apto 82",
                "Alphaville", "Minas Gerais", "MG", "23456-789");
        endereco1.setAluno(aluno3);
        endereco2.setAluno(aluno3);
        aluno3.setEnderecos(List.of(endereco1, endereco2));

        // Criando um aluno com Curso e Professor no mesmo curso
        Aluno aluno4 = new Aluno(null, "Junior Pereira Guedes", "864",
                getCalendarDate(), "junior.pereira@email.com");
        Curso curso1 = new Curso(null, "Java EE", "JEE");
        Curso curso2 = new Curso(null, "Java Spring Boot", "JSB");

        MaterialCurso materialCurso1 = new MaterialCurso(null, "http://www.cursojee.com.br");
        materialCurso1.setCurso(curso1);
        curso1.setMaterialCurso(materialCurso1);

        MaterialCurso materialCurso2 = new MaterialCurso(null, "http://www.cursojsb.com.br");
        materialCurso2.setCurso(curso2);
        curso2.setMaterialCurso(materialCurso2);

        Professor professor1 = new Professor(null, "Carlos da Silva", "246",
                "carlos.silva@email.com.br", List.of(curso1));
        Professor professor2 = new Professor(null, "Wellington Carneiro", "187",
                "wellington.carneiro@email.com.br", List.of(curso2));

        curso1.setAlunos(List.of(aluno4));
        curso2.setAlunos(List.of(aluno4));

        curso1.setProfessor(professor1);
        curso2.setProfessor(professor2);

        aluno4.setCursos(List.of(curso1, curso2));

//        alunoModel.create(aluno);
//        alunoModel.create(aluno2);
//        alunoModel.create(aluno3);
        alunoModel.create(aluno4);
    }

    public static void buscarAlunoPorId(Long id) {
        Aluno aluno = alunoModel.findById(id);
        System.out.println("Aluno: " + aluno.getNomeCompleto());
    }

    public static void buscarTodosOsAlunos() {
        List<Aluno> alunos = alunoModel.findAll();
        System.out.println("Lista de alunos encontrados: ");

        for (Aluno aluno : alunos) {
            System.out.println("Aluno: " + aluno.getNomeCompleto());
        }
    }

    public static void atualizarAluno() {
        Aluno aluno = alunoModel.findById(5L);
        System.out.println("Aluno encontrado: " + aluno.getNomeCompleto());

        String emailAtual = aluno.getEmail();
        System.out.println("Email atual: " + emailAtual);
        aluno.setEmail("novo.email.aluno@email.com");

        alunoModel.update(aluno);

        Aluno alunoAtualizado = alunoModel.findById(5L);
        System.out.println("Email atualizado: " + alunoAtualizado.getEmail());

    }

    public static void deletarAluno(Long id) {
        Aluno alunoEncontrado = alunoModel.findById(id);
        alunoModel.delete(alunoEncontrado);
    }

    public static Calendar getCalendarDate() {
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.set(Calendar.YEAR, 1978);
        calendarDate.set(Calendar.MONTH, 8);
        calendarDate.set(Calendar.DAY_OF_MONTH, 25);
        calendarDate.set(Calendar.HOUR_OF_DAY, 0);
        calendarDate.set(Calendar.MINUTE, 0);
        calendarDate.set(Calendar.SECOND, 0);
        calendarDate.set(Calendar.MILLISECOND, 0);
        return calendarDate;
    }

}
