export function imprimirTablaDesdeId(tableId: string, titulo: string): void {
  const tabla = document.getElementById(tableId);

  if (!tabla) {
    window.print();
    return;
  }

  const ventana = window.open('', '_blank', 'width=1000,height=800');
  if (!ventana) {
    return;
  }

  const estilos = `
    <style>
      body { 
        font-family: 'Times New Roman', serif; 
        padding: 20px; 
        color: #1e3a5f;
        background: #e7eef7;
      }
      h1 { 
        text-align: center; 
        margin-bottom: 20px; 
        color: #1e3a5f;
        border-bottom: 3px solid #9db8d1;
        padding-bottom: 6px;
      }
      table { 
        width: 100%; 
        border-collapse: collapse; 
        background: white;
      }
      th, td { 
        border: 1px solid #9db8d1; 
        padding: 10px; 
        text-align: left;
        font-size: 15px;
      }
      th { 
        background: #1e3a5f; 
        color: white; 
      }
      tr:nth-child(even) { 
        background: #d3dfec; 
      }
      tr:nth-child(odd) { 
        background: #f5f7fa; 
      }
      img { 
        max-width: 80px; 
        height: auto; 
        border-radius: 8px; 
        border: 1px solid #9db8d1;
      }
    </style>
  `;

  ventana.document.write(`
    <html>
      <head>${estilos}</head>
      <body>
        <h1>${titulo}</h1>
        ${tabla.outerHTML}
      </body>
    </html>
  `);

  ventana.document.close();
  ventana.focus();
  ventana.print();
  ventana.close();
}
