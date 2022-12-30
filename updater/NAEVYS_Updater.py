# Autor: Christopher Gabriel Pedraza Pohlenz

# Crear ejecutable del script con la siguiente linea: "pyinstaller --onefile NAEVYS_Updater.py"

# Libreria necesaria para hacer peticiones a paginas web
import requests

# Colores para el texto
# Referencia: https://stackoverflow.com/questions/287871/how-do-i-print-colored-text-to-the-terminal
BLUE = '\033[94m'
CYAN = '\033[96m'
GREEN = '\x1b[1;32;40m'
NORMAL = '\033[0m'

# Obtener el archivo JSON del ultimo release del repositorio
response = requests.get("https://api.github.com/repos/christopher-pedraza/NAEVYS/releases/latest").json()

# Crea una lista con los datos de los assets (archivos del release)
list = response["assets"]

# Especifica al usuario la version que se esta actualizando
print(CYAN + "\n\n   ~~~ NAEVYS Version '" + response["name"] + "' ~~~\n" + NORMAL)

# Recorre la lista de los assets
for asset in list:
	# Despliega el nombre del archivo
	print("Downloading '" + BLUE + asset["name"] + NORMAL + "'" + NORMAL, end=" .")
	# Obtiene el archivo a partir del enlace del asset
	file_dir = requests.get(asset["browser_download_url"])
	print(".", end="")
	# Crea el archivo
	open(asset["name"], "wb").write(file_dir.content)
	print(". ", end="")
	print(GREEN + "Finished." + NORMAL)

# Espera un input para que no se cierre la terminal
input(NORMAL + "\nPress <Enter> to continue...")