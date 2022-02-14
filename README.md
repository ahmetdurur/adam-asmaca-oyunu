# Adam Asmaca Yapay Zeka ( Java  )
İnsanın sorup yapay zekanın tahmin ettiği ve yapay zekanın sorup insanın tahmin ettiği iki modu bulunan adam asmaca oyunu. 
Herhangi bir java derleyicisinde açıp compile edebilir yada indirdiginiz dosyanın bulundugu dizine windows için cmd ile linux için terminal ile yerlesip
**javac main.java** komutunu çalıştırarak oynayabilirsiniz oyun konsolda çalışmaktadır.

src/resources/turkish_dictionary.txt dosyasında bulunan sözlük referanslı bir oyundur seçeceğiniz kelime sözlükte bulunmak zorundadır
eğer yapayzeka kelime seçiyorsa sözlükte bulunduğu garanti edilir.

Oyunmodu A yı seçerseniz yapayzeka size seçim yapacağınız kelime uzunluğunu söyler siz kelimenizi seçince yapay zeka harf tahmin eder doğruysa input olarak
tahminlerinizi alır. Tahmin hakkınız bitene ya da tüm tahminleriniz doğru olup kelime açılana kadar oyun devam eder. 

OyunModu B yi seçerseniz siz kelime uzunluğu belirlersiniz yapay zeka o uzunlukta bir kelimeyi sözlükten seçer. Siz harf tahmini yaparsınız eğer doğruysa 
yapay zeka harfi açıp gösterir yanlışsa tahmin hakkınız 1 azaltılır ve tahmin hakkınız bitene kadar  yeni yeni tahminler yaparsınız. Eğer kelime tamamen açılmışsa 
oyunu kazanmış olursunuz eğer tahmin hakkı bitmiş olursa oyunu kaybetmiş olursunuz.

Kurallar

  - Her iki oyun modunda da tahmin hakkı 10'dur.
  - Eğer doğru tahmin yaparsanız tahmin hakkınız azalmaz.
  - Eğer yanlış tahmin yaparsanız tahmin hakkınız 1 azalır

Oyunda enemy yapay zekayı seçebilirsiniz ancak her iki oyun modu içinde şimdilik tek yapay zeka mekaniği vardır. Yani 4 yapay zekadan herhangi birini seçmeniz oyun mekaniğini
şu an için etkilemez. Ancak her iki oyun modu içinde sunulan 4 yapay zekanın her birisi için farklı oyun zorlukları eklenecektir.

Enemy yapay zekalar
  - HARBINGER ![This is an image](https://gaming-tools.com/warcraft-3/wp-content/uploads/sites/2/2020/04/ObsidianDestroyer.jpg)
  - CRIXALIS  ![This is an image](https://gaming-tools.com/warcraft-3/wp-content/uploads/sites/2/2020/04/Sandking.jpg)
  - NESSAJ  ![This is an image](https://gaming-tools.com/warcraft-3/wp-content/uploads/sites/2/2020/04/ChaosKnight.jpg)
  - PUDGE  ![This is an image](https://gaming-tools.com/warcraft-3/wp-content/uploads/sites/2/2020/04/Butcher.jpg)
