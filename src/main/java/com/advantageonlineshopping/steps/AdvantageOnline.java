package com.advantageonlineshopping.steps;

import com.advantageonlineshopping.core.ConectarBancoTest;
import com.advantageonlineshopping.core.UTIL;
import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AdvantageOnline {

    private WebDriver driver;
    private UTIL util;
    private ConectarBancoTest banco;

    @After
    public void fechar() {
        driver.quit();
    }

    @Dado("que eu acesso o site do Advantage Online Shopping")
    public void queEuAcessoOSiteDoAdvantageOnlineShopping() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://advantageonlineshopping.com");
        util = new UTIL(driver);
        banco = new ConectarBancoTest();
    }

    @Então("eu verifico se o site está disponível")
    public void euVerificoSeOSiteEstáDisponível() {
        util.esperarElemento(By.id("see_offer_btn"), 10);
    }

    @Dado("que eu clico na opção {string}")
    public void queEuClicoNaOpção(String nomeLink) throws InterruptedException {
        Thread.sleep(1000);
        util.clicarTextoLink(nomeLink);
    }

    @Dado("clico no botão {string}")
    public void clicoNoBotão(String string) {
        util.clicarBotao(By.id("see_offer_btn"));
    }

    @Então("eu valido as informações do produto")
    public void euValidoAsInformaçõesDoProduto() throws Exception {
        util.esperarElemento(By.xpath("//*[@id=\"Description\"]/h1"), 5);

        String nomeProdutoBase = banco.pesquisarBancoDados("NAME_PRODUCT");
        String nomeProdutoPage = util.obterTexto(By.xpath("//*[@id=\"Description\"]/h1"));
        Assert.assertEquals(nomeProdutoBase, nomeProdutoPage);

        String customizationBase = banco.pesquisarBancoDados("CUSTOMIZATION");
        String customizationPage = util.obterTexto(By.xpath("//div[3]//article[2]/div[1]/label[2]"));
        Assert.assertEquals(customizationBase, customizationPage);

        String displayBase = banco.pesquisarBancoDados("DISPLAY");
        String displayPage = util.obterTexto(By.xpath("//div[3]/section/article[2]/div[2]/label[2]"));
        Assert.assertEquals(displayBase, displayPage);

        String corBase = banco.pesquisarBancoDados("COLOR");
        String corPage = util.obterTitleElemento(By.cssSelector(".colorSelected"));
        Assert.assertEquals(corBase, corPage);

    }

    @Dado("altero a cor do produto para {string}")
    public void alteroACorDoProdutoPara(String cor) throws Exception {
        util.esperarElementoClicavel(By.cssSelector("[id='bunny']"), 10);
        if (cor.equals("corBase")) {
            String corBase = banco.pesquisarBancoDados("COLOR");
            util.clicarBotao(By.cssSelector("." + corBase + "[id='bunny']"));
        }
        if (cor.equals("RED")) {
            Thread.sleep(1000);
            util.clicarBotao(By.cssSelector("." + cor + "[id='bunny']"));
        }
    }

    @Dado("adiciono o produto no carrinho")
    public void adicionoOProdutoNoCarrinho() {
        util.clicarBotao(By.cssSelector("[name='save_to_cart']"));
        util.clicarBotao(By.id("menuCart"));
    }

    @Então("eu valido que produto foi adicionado ao carrinho com a cor selecionada")
    public void euValidoQueProdutoFoiAdicionadoAoCarrinhoComACorSelecionada() throws Exception {
        util.esperarElementoClicavel(By.id("checkOutButton"), 5);
        String corPage = util.obterTitleElemento(By.cssSelector(".productColor"));
        String corBase = banco.pesquisarBancoDados("COLOR");
        Assert.assertEquals(corBase, corPage);
    }

    @Dado("que eu pesquiso o produto pela lupa")
    public void queEuPesquisoOProdutoPelaLupa() throws Exception {
        Thread.sleep(1000);
        util.clicarBotao(By.id("menuSearch"));
        Thread.sleep(1000);
        String textoBase = banco.pesquisarBancoDados("NAME_PRODUCT");
        util.escrever(By.id("autoComplete"), textoBase);
    }

    @Dado("seleciono o produto pesquisado")
    public void selecionoOProdutoPesquisado() throws InterruptedException {
        Thread.sleep(2000);
        util.esperarElementoVisivel(By.xpath("//*[@id=\"output\"]//a[2]"), 5);
        util.clicarBotao(By.xpath("//*[@id=\"output\"]//a[2]"));
    }

    @Dado("altero a quantidade do produto")
    public void alteroAQuantidadeDoProduto() {
        util.esperarElemento(By.xpath("//*[@id=\"Description\"]/h1"), 5);
        util.clicarBotao(By.xpath("//*[@id=\"productProperties\"]/div[2]//div[3]"));
        util.clicarBotao(By.xpath("//div[3]//article[2]/div[1]/label[2]"));
    }


    @Então("verifico o valor total dos prdutos")
    public void verificoOValorTotalDosPrdutos() {

        util.esperarElemento(By.xpath("//*[@id=\"shoppingCart\"]//tr[1]/td[2]/span[2]"), 10);

        String valorTotalPage = util.obterTexto(By.xpath("//*[@id=\"shoppingCart\"]//tr[1]/td[2]/span[2]"));

        String qtd = util.obterTexto(By.xpath("//*[@id=\"shoppingCart\"]//td[5]/label[2]"));
        Integer numero = Integer.parseInt(qtd);
        double valorUnitario = 449.99;
        double valorTotal = valorUnitario * numero;
        String valorTotalSoma = String.valueOf(valorTotal);

        Assert.assertEquals("$" + valorTotalSoma, valorTotalPage);

    }

    @Então("atualizo a base com a cor selecionada para o produto")
    public void atualizoABaseComACorSelecionadaParaOProduto() throws Exception {
        String corPage = util.obterTitleElemento(By.cssSelector(".productColor"));
        banco.alterarBancoDados("COLOR", corPage);
    }

    @Então("eu removo o produto do carrinho")
    public void euRemovoOProdutoDoCarrinho() {
        util.clicarBotao(By.xpath("//*[@id=\"shoppingCart\"]//td[6]//a[3]"));
    }

    @Então("verifico se o carrinho está vazio")
    public void verificoSeOCarrinhoEstáVazio() {
        util.clicarBotao(By.id("menuCart"));
        String texto = util.obterTexto(By.cssSelector("[style='position: relative; background-color: white'] .items .roboto-regular"));
        Assert.assertEquals("(0)", texto);
    }

}
