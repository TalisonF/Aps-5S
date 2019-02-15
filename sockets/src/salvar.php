<pre>
<?php 

    print_r($_POST);
    
    $nome_temporario = $_FILES['Arquivo']['tmp_name']; 
    
    $nome_real = $_FILES['Arquivo']['name'];

    copy($nome_temporario,$nome_real);

    echo('<h1 style="color:red">Salvo o arquivo caraio</h1>');
    
    
?>