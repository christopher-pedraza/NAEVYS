# Compilador para crear un ejecutable del autoupdater de NAEVYS

import PyInstaller.__main__

PyInstaller.__main__.run([
    'NAEVYS_Updater.py',
    '--onefile',
    '--icon=res\\icon.ico'
])