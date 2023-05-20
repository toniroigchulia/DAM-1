import tkinter as tk
from tkinter import ttk

def on_click():

    print("Ayuda porfavor")


def crear_frame(master):
    
    frame = ttk.Frame(master, width=200, height=200, borderwidth=10, relief=tk.GROOVE)
    
    frame.pack_propagate(False)
    frame.pack(side='left')
    return frame


def main():

    window = tk.Tk()
    window.title('Windows and Widgets')
    window.geometry('800x500')
    
    frame = crear_frame(window)
    
    label = ttk.Label(window, text="Tremendo texto")
    label.pack()
    
    entry = ttk.Entry(frame)
    entry.pack()
    
    button = ttk.Button(frame, text="Tremendo Boton", command=on_click)
    button.pack()
    
    window.mainloop()


    

if __name__ == '__main__':

    main()
    
